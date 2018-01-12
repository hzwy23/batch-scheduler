function wisrcStatemachine(params) {
    var o = this;
    var instance = (function () {
        var inst = jsPlumb.getInstance({
            Endpoint: ["Dot", {radius: 2}],
            Connector: "StateMachine",
            HoverPaintStyle: {
                stroke: "#ff2a12",
                strokeWidth: 3
            },
            ConnectionOverlays: [
                ["Arrow", {
                    location: 1,
                    id: "arrow",
                    length: 11,
                    width: 11,
                    foldback: 0.8
                }]
            ],
            Container: "canvas"
        });
        inst.registerConnectionType("basic", {
            anchor: "Continuous",
            connector: "StateMachine"
        });
        return inst;
    })();

    var Default = {
        addId: "",
        deleteId: "",
        save: {
            id: "",
            url: "",
            success: function (data) {
            }
        },
        connect: {
            click: function (c) {
            },
            dblclick: function (c) {
                instance.deleteConnection(c);
            }
        }
    };

    jsPlumb.extend(Default, params);

    // bind a click listener to each connection; the connection is deleted. you could of course
    // just do this: jsPlumb.bind("click", jsPlumb.detach), but I wanted to make it clear what was
    // happening.
    instance.bind("dblclick", function (c) {
        // 连接线双击事件
        Default.connect.dblclick(c)
    });

    instance.bind('click', function (c) {
        // 连接线单击事件。
        Default.connect.click(c);
    });

    // bind a connection listener. note that the parameter passed to this function contains more than
    // just the new connection - see the documentation for a full list of what is included in 'info'.
    // this listener sets the connection's internal
    // id as the label overlay's text.
    instance.bind("connection", function (info) {
        if (info.source.id === info.target.id) {
            console.log("不能自己连接自己");
            instance.deleteConnection(info.connection);
            return
        }
        // 回路检查
        o.checklookback(info);
    });

    jsPlumb.fire("jsPlumbDemoLoaded", instance);

    this.instance = instance;
    this.total = $(instance.getContainer()).find(".start-btn").length + 1;

    // 事件绑定
    // 添加:
    $(Default.addId).click(function () {
        o.newNode();
    });

    $(Default.deleteId).click(function () {
        var ele = $(".itemSelect");
        if (ele.length === 0) {
            $.Notify({
                message: "请选择需要删除的节点",
                type: "warning"
            });
            return
        }

        $.Hconfirm({
            body: "点击确定，删除选中结点",
            callback: function () {
                var id = ele[0].id;
                var arr = new Array();
                var e = {};
                e.jobKey = id;
                arr.push(e);

                $.HAjaxRequest({
                    url: "/v1/dispatch/group/define/task/list/delete",
                    type: "POST",
                    data: {JSON: JSON.stringify(arr)},
                    success: function () {
                        o.deleteElement(id);
                        $.Notify({
                            message: "删除任务组中配置任务成功",
                            type: "success"
                        });
                        DispatchGroupTaskObj.refresh();
                    }
                });
            }
        });

    });

    $(Default.save.id).click(function () {
        var conns = instance.getAllConnections().concat();
        var connections = new Array();
        for (var i = 0; i < conns.length; i++) {
            var e = {};
            e.source = conns[i].source.id;
            e.target = conns[i].target.id;
            connections.push(e);
        }

        var eles = $(instance.getContainer()).find(".start-btn");
        var elements = new Array();
        for (var i = 0; i < eles.length; i++) {
            var e = {};
            e.id = eles[i].id;
            e.left = eles[i].style.left;
            e.top = eles[i].style.top;
            e.text = eles[i].querySelector("span").innerHTML;
            elements.push(e);
        }
        Default.save.success(connections, elements);
    })
}

wisrcStatemachine.prototype.init = function (elements, connections) {
    // 创建元素
    for (var i = 0; i < elements.length; i++) {
        this.newNode({
            id: elements[i].id,
            left: elements[i].left,
            top: elements[i].top,
            text: elements[i].text,
            btnAdd: false
        });
    }

    for (var j = 0; j < connections.length; j++) {
        this.getInstance().connect({
            source: connections[j].source,
            target: connections[j].target,
            type: "basic"
        })
    }
};


wisrcStatemachine.prototype.getInstance = function () {
    return this.instance;
};


wisrcStatemachine.prototype.getEvent = function () {
    if (window.event) {
        return window.event;
    }
    var func = Hutils.getEvent.caller;
    while (func != null) {
        var arg0 = func.arguments[0];
        if (arg0) {
            if ((arg0.constructor == Event || arg0.constructor == MouseEvent
                    || arg0.constructor == KeyboardEvent)
                || (typeof(arg0) == "object" && arg0.preventDefault
                    && arg0.stopPropagation)) {
                return arg0;
            }
        }
        func = func.caller;
    }
    return null;
};

wisrcStatemachine.prototype.submitEdit = function (obj) {
    // 取消后续事件
    if (window.event != undefined) {
        window.event.cancelBubble = true;
    } else {
        var event = this.getEvent();
        event.stopPropagation()
    }

    var text = obj.value;
    var parentNode = obj.parentNode;
    var show = parentNode.parentNode;
    try {
        parentNode.remove();
        var span = show.querySelector("span");
        span.innerHTML = text;
        span.style.display = "";
    } catch (e) {
        console.log("编辑框已经被删除，修改完成。")
    }
};

wisrcStatemachine.prototype.WisrcEditSpan = function (obj) {
    var o = this;
    // 取消后续事件
    if (window.event != undefined) {
        window.event.cancelBubble = true;
    } else {
        var event = this.getEvent();
        event.stopPropagation()
    }

    var text = obj.innerHTML;
    obj.style.display = "none";

    var div = document.createElement("div");
    div.className = "input-group";
    div.style.marginLeft = "-30px";
    div.style.marginRight = "-30px";

    var input = document.createElement("input");
    input.value = text;
    input.className = "flowchart-element-text-input form-control";
    input.position = "fixed";

    div.appendChild(input);
    obj.parentNode.appendChild(div);
    input.focus();
    if (input.attachEvent) {
        input.attachEvent('onkeydown', function (e) {
            if (e.keyCode === 13) {
                o.submitEdit(this);
            }
        });
        input.attachEvent('onblur', function () {
            o.submitEdit(this);
        })
    } else {
        input.addEventListener('keydown', function (e) {
            if (e.keyCode === 13) {
                o.submitEdit(this);
            }
        }, false);
        input.addEventListener('blur', function (e) {
            o.submitEdit(this);
        }, false);
    }
};

wisrcStatemachine.prototype.deleteAll = function () {
    var o = this;
    // 获取所有的id
    $(o.instance.getContainer()).find(".start-btn").each(function (index, element) {
        // 根据id删除
        var id = element.id;

        var conn = o.instance.getAllConnections().concat();
        for (var i = 0; i < conn.length; i++) {
            if (conn[i].source.id === id || conn[i].target.id === id) {
                o.instance.deleteConnection(conn[i]);
            }
        }
        $("#" + id).remove();
    });
};

wisrcStatemachine.prototype.deleteElement = function (id) {
    var conn = this.instance.getAllConnections().concat();
    for (var i = 0; i < conn.length; i++) {
        if (conn[i].source.id === id || conn[i].target.id === id) {
            this.instance.deleteConnection(conn[i]);
        }
    }
    $("#" + id).remove();
};

wisrcStatemachine.prototype.dbclk = function (obj) {
    var rightValue = $("#drawer-task-handle").css("right");
    if (rightValue === "-300px") {
        $("#drawer-task-handle").animate({
            right: "0px",
            opacity: '1'
        });
    }

    // TODO
    // 显示详细信息
};

wisrcStatemachine.prototype.newNode = function (params) {
    uid = jsPlumbUtil.uuid();
    var o = this;

    var Default = {
        id: uid,
        text: "测试" + o.total++,
        /** inner,放入新建的元素内部
         /** bottom: 放入新建的元素下方
         **/
        textPosition: "bottom",
        icon: "show-connect-line glyphicon glyphicon-screenshot",
        left: "0px",
        top: "0px",
        btnAdd: true
    };

    jsPlumb.extend(Default, params);
    var div = document.createElement("div");
    div.className = "start-btn itemCreate";
    div.id = Default.id;
    div.style.textAlign = "center";
    div.style.left = Default.left;
    div.style.top = Default.top;

    if (div.attachEvent) {
        div.attachEvent('ondblclick', function (e) {
            o.dbclk(this);
        });

        // 单击事件
        div.attachEvent('onclick', function () {
            // 取消后续事件
            if (window.event != undefined) {
                window.event.cancelBubble = true;
            } else {
                var event = getEvent();
                event.stopPropagation()
            }
            $(".start-btn").removeClass("itemSelect");
            div.className += " itemSelect";
        })
    } else {
        div.addEventListener('dblclick', function (e) {
            o.dbclk(this);
        }, false);

        // 单击事件
        div.addEventListener('click', function (e) {
            // 取消后续事件
            if (window.event != undefined) {
                window.event.cancelBubble = true;
            } else {
                var event = getEvent();
                event.stopPropagation()
            }
            $(".start-btn").removeClass("itemSelect");
            div.className += " itemSelect";
        }, false)
    }

    var i = document.createElement("i");
    i.className = Default.icon;
    i.style.fontSize = "16px";
    div.appendChild(i);

    var text = document.createElement("span");
    text.innerHTML = Default.text;
    if (Default.textPosition == "bottom") {
        text.className = "flowchart-element-text-bottom";
    } else {
        text.className = "flowchart-element-text-inner";
    }


    $(text).dblclick(function () {
        o.WisrcEditSpan(this)
    });
    div.appendChild(text);


    if (Default.btnAdd) {
        $(this.instance.getContainer()).find(".itemCreate").remove();
        $(".hzwy23-flowchart-workspace").unbind("scroll");
        this.instance.getContainer().appendChild(div);

        var $obj = $(o.instance.getContainer());
        var offsetLeft = $obj.offset().left;
        var offsetTop = $obj.offset().top;

        var y = $obj.parent().scrollTop();
        var x = $obj.parent().scrollLeft();

        $(o.instance.getContainer()).bind('mousemove', function (e) {
            var scrollTop = $(this).parent().scrollTop() - y;
            var scrollLeft = $(this).parent().scrollLeft() - x;

            $(div).css({
                left: e.clientX - offsetLeft - 30 + scrollLeft,
                top: e.clientY - offsetTop - 30 + scrollTop
            });
        });

        $(div).on('click', function () {
            $(o.instance.getContainer()).unbind("mousemove");
            $(div).unbind("click").unbind("contextmenu").removeClass("itemCreate");
            o.initNode(div);
        });

        $(div).bind("contextmenu", function () {
            $(o.instance.getContainer()).unbind("mousemove");
            $(div).remove();
            return false;
        })
    } else {
        $(div).removeClass("itemCreate");
        this.instance.getContainer().appendChild(div);
        setTimeout(function () {
            o.initNode(div);
        }, 500);
    }
    return div;
};

wisrcStatemachine.prototype.initNode = function (el) {
    // initialise draggable elements.
    this.instance.draggable(el);

    this.instance.makeSource(el, {
        filter: ".show-connect-line",
        anchor: "Continuous",
        connectorStyle: {
            stroke: "#5c96bc",
            strokeWidth: 2,
            outlineStroke: "transparent",
            outlineWidth: 4
        },
        connectionType: "basic",
        extract: {
            "action": "the-action"
        }
    });

    this.instance.makeTarget(el, {
        dropOptions: {hoverClass: "dragHover"},
        anchor: "Continuous",
        allowLoopback: true
    });

    // this is not part of the core demo functionality; it is a means for the Toolkit edition's wrapped
    // version of this demo to find out about new nodes being added.
    //
    this.instance.fire("jsPlumbDemoNodeAdded", el);
};

wisrcStatemachine.prototype.checklookback = function (info) {
    var sid = info.source.id;
    var tid = info.target.id;

    var conn = this.instance.getAllConnections().concat();
    for (var i = 0; i < conn.length; i++) {
        if (conn[i].source.id === tid && conn[i].target.id === sid) {
            this.instance.deleteConnection(info.connection);
            $.Notify({
                message: "两个结点之间，不能互联",
                type: "info"
            })
        }
    }
};