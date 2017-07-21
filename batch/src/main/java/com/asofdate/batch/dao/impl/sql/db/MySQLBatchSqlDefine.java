package com.asofdate.batch.dao.impl.sql.db;

import com.asofdate.batch.dao.impl.sql.SQLFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Configuration
public class MySQLBatchSqlDefine implements SQLFactory {
    private final String sys_rdbms_073 = "select t.suite_key,d.up_suite_key from dispatch_batch_group_relation t inner join dispatch_group_dependency d on t.suite_key = d.suite_key where t.batch_id = ?";
    private final String sys_rdbms_075 = "select t.job_key, d.up_job_key from dispatch_group_task_relation t inner join  dispatch_task_dependency d on t.job_key = d.job_key where t.group_id = ?";
    private final String sys_rdbms_102 = "select as_of_date from dispatch_batch_define where batch_id = ? and complete_date >= as_of_date";
    private final String sys_rdbms_104 = "select t.arg_id, t.arg_type,a.arg_type_desc,t.arg_value,t.code_number,t.create_user,t.create_date,t.modify_user,t.modify_date,t.domain_id,t.arg_desc,t.bind_as_of_date from dispatch_argument_define t left join dispatch_argument_type_attr a on t.arg_type = a.arg_type where t.domain_id = ?";
    private final String sys_rdbms_105 = "select t.uuid,t.batch_id,t.arg_id,t.arg_value,t.domain_id from dispatch_batch_argument_rel t where t.domain_id = ?";
    private final String sys_rdbms_106 = "select t.suite_key,t.batch_id,t.group_id,t.domain_id from dispatch_batch_group_relation t where t.domain_id = ? and t.batch_id = ?";
    private final String sys_rdbms_107 = "select t.batch_id,t.code_number,t.batch_desc,t.batch_status, t.as_of_date,t.start_date,t.ret_msg, t.complete_date, t.end_date,t.domain_id,a.batch_status_desc,t.pagging_freq,t.pagging_freq_mult,r.pagging_freq_mult_desc from dispatch_batch_define t inner join dispatch_batch_status_attr a on t.batch_status = a.batch_status inner join dispatch_batch_pagging_attr r on t.pagging_freq_mult = r.pagging_freq_mult where t.domain_id = ?";
    private final String sys_rdbms_108 = "select t.group_id,t.code_number,t.group_desc,t.create_user,t.create_date,t.modify_user,t.modify_date,t.domain_id from dispatch_group_define t where t.domain_id = ?";
    private final String sys_rdbms_109 = "select t.job_key,t.group_id,t.task_id,t.domain_id from dispatch_group_task_relation t where t.domain_id = ?";
    private final String sys_rdbms_110 = "select t.uuid,t.task_id,t.arg_id,t.arg_value,t.domain_id,t.sort_id from dispatch_task_argument_rel t where t.domain_id = ?";
    private final String sys_rdbms_111 = "select t.task_id,t.code_number,t.task_desc,t.task_type,a.task_type_desc,t.create_user,t.create_date,t.modify_date,t.modify_user,t.domain_id,t.script_file from dispatch_task_define t inner join dispatch_task_type_attr a on t.task_type = a.task_type where t.domain_id = ?";
    private final String sys_rdbms_112 = "select t.uuid,t.suite_key,t.up_suite_key,t.domain_id from dispatch_group_dependency t inner join dispatch_batch_group_relation r on t.suite_key = r.suite_key where t.domain_id = ? and r.batch_id = ?";
    private final String sys_rdbms_113 = "select uuid,job_key,up_job_key,domain_id from dispatch_task_dependency where domain_id = ?";
    private final String sys_rdbms_114 = "select uuid,job_key,arg_id,arg_value,domain_id from dispatch_group_argument_rel where domain_id = ?";
    private final String sys_rdbms_119 = "insert into dispatch_argument_define(arg_id,arg_type,arg_value,code_number,create_user,create_date,modify_user,modify_date,domain_id,arg_desc,bind_as_of_date) values(?,?,?,?,?,now(),?,now(),?,?,?)";
    private final String sys_rdbms_120 = "delete from dispatch_argument_define where arg_id = ? and domain_id = ?";
    private final String sys_rdbms_121 = "update dispatch_argument_define set modify_user = ?, modify_date = now(), bind_as_of_date = ?, arg_desc = ?, arg_value = ? where arg_id = ? and domain_id = ?";
    private final String sys_rdbms_122 = "delete from dispatch_group_define where group_id = ? and domain_id = ?";
    private final String sys_rdbms_123 = "update dispatch_group_define set group_desc = ? where group_id = ? and domain_id = ?";
    private final String sys_rdbms_124 = "insert into dispatch_group_define(group_id,code_number,group_desc,create_user,create_date,modify_user,modify_date,domain_id) values(?,?,?,?,now(),?,now(),?)";
    private final String sys_rdbms_125 = "insert into dispatch_task_define(task_id,code_number,task_desc,task_type,create_user,create_date,modify_date,modify_user,domain_id,script_file) values(?,?,?,?,?,now(),now(),?,?,?)";
    private final String sys_rdbms_126 = "update dispatch_task_define set task_desc = ?, task_type = ?,script_file = ? where task_id = ? and domain_id = ?";
    private final String sys_rdbms_127 = "delete from dispatch_task_define where task_id = ? and domain_id = ?";
    private final String sys_rdbms_128 = "insert into dispatch_batch_define(batch_id,code_number,batch_desc,batch_status,as_of_date,ret_msg,complete_date,domain_id,pagging_freq,pagging_freq_mult) values(?,?,?,?,?,?,?,?,?,?)";
    private final String sys_rdbms_129 = "delete from dispatch_batch_define where batch_id = ? and domain_id = ?";
    private final String sys_rdbms_130 = "update dispatch_batch_define set batch_desc = ?, as_of_date = ? , complete_date = ?,pagging_freq = ?, pagging_freq_mult = ? where batch_id = ? and domain_id = ?";
    private final String sys_rdbms_131 = "select batch_status from dispatch_batch_define where batch_id = ?";
    private final String sys_rdbms_132 = "select t.uuid,t.task_id,t.arg_id,t.arg_value,t.sort_id,t.domain_id,d.arg_type,a.arg_type_desc,d.arg_desc,d.code_number,d.arg_value as fixed_arg_value from dispatch_task_argument_rel t inner join dispatch_argument_define d on t.arg_id = d.arg_id inner join dispatch_argument_type_attr a on d.arg_type = a.arg_type where t.task_id = ? order by sort_id asc";
    private final String sys_rdbms_133 = "select t.job_key,t.group_id,t.task_id,t.domain_id,d.task_desc,d.task_type,a.task_type_desc,d.code_number from dispatch_group_task_relation t inner join dispatch_task_define d on t.task_id = d.task_id inner join dispatch_task_type_attr a on d.task_type = a.task_type where t.group_id = ?";
    private final String sys_rdbms_134 = "select t.uuid,t.job_key,t.up_job_key,t.domain_id,r.group_id,r.task_id,d.task_desc,d.code_number from dispatch_task_dependency t inner join dispatch_group_task_relation r on t.up_job_key = r.job_key inner join dispatch_task_define d on r.task_id = d.task_id where t.job_Key = ?";
    private final String sys_rdbms_135 = "select t.job_key,t.arg_id,t.arg_value,t.domain_id,r.group_id,r.task_id from dispatch_group_argument_rel t inner join dispatch_group_task_relation r on t.job_key = r.job_key where t.job_key = ?";
    private final String sys_rdbms_136 = "select t.task_id from dispatch_group_task_relation t where t.job_key = ?";
    private final String sys_rdbms_137 = "select t.suite_key,t.batch_id,t.group_id,t.domain_id,d.group_desc,d.code_number from dispatch_batch_group_relation t inner join dispatch_group_define d on t.group_id = d.group_id where batch_id = ?";
    private final String sys_rdbms_138 = "select t.uuid,t.suite_key,t.up_suite_key,t.domain_id,r.group_id,d.group_desc,d.code_number from dispatch_group_dependency t inner join dispatch_batch_group_relation r on t.up_suite_key = r.suite_key inner join dispatch_group_define d on r.group_id = d.group_id where t.suite_key = ?";
    private final String sys_rdbms_139 = "select distinct r.batch_id,d.arg_id,d.code_number,d.arg_desc,a.arg_value,a.uuid,d.bind_as_of_date,r.domain_id from dispatch_batch_group_relation r inner join dispatch_group_task_relation g on r.group_id=g.group_id inner join dispatch_task_argument_rel t on g.task_id = t.task_id inner join dispatch_argument_define d on t.arg_id = d.arg_id left join dispatch_batch_argument_rel a on a.batch_id = r.batch_id and a.arg_id = t.arg_id where d.arg_type = '4' and r.batch_id = ?";
    private final String sys_rdbms_140 = "update dispatch_batch_define set batch_status = ? where batch_id = ?";
    private final String sys_rdbms_141 = "update dispatch_task_argument_rel set sort_id = ? where uuid = ?";
    private final String sys_rdbms_142 = "delete from dispatch_task_argument_rel where uuid = ?";
    private final String sys_rdbms_143 = "select arg_id,arg_desc,arg_value,arg_type,domain_id from dispatch_argument_define where arg_id = ?";
    private final String sys_rdbms_144 = "insert into dispatch_task_argument_rel(uuid,task_id,arg_id,domain_id,arg_value,sort_id) values(uuid(),?,?,?,?,?)";
    private final String sys_rdbms_145 = "update dispatch_task_argument_rel set arg_value = ? where uuid = ?";
    private final String sys_rdbms_146 = "update dispatch_group_argument_rel set arg_value = ? where job_key = ? and arg_id = ?";
    private final String sys_rdbms_147 = "delete from dispatch_group_task_relation where job_key = ?";
    private final String sys_rdbms_148 = "insert into dispatch_group_task_relation(job_key,group_id,task_id,domain_id) values(?,?,?,?)";
    private final String sys_rdbms_149 = "insert into dispatch_group_argument_rel(uuid,job_key,arg_id,arg_value,domain_id) values(uuid(),?,?,?,?)";
    private final String sys_rdbms_150 = "select t.job_key,t.group_id,t.task_id,d.task_desc,t.domain_id from dispatch_group_task_relation t inner join dispatch_task_define d on t.task_id = d.task_id where t.group_id = ?";
    private final String sys_rdbms_151 = "insert into dispatch_task_dependency(uuid,job_key,up_job_key,domain_id) values(uuid(),?,?,?)";
    private final String sys_rdbms_152 = "delete from dispatch_task_dependency where uuid = ?";
    private final String sys_rdbms_153 = "delete from dispatch_group_dependency where uuid = ?";
    private final String sys_rdbms_154 = "insert into dispatch_batch_group_relation(suite_key,batch_id,group_id,domain_id) values(?,?,?,?)";
    private final String sys_rdbms_155 = "delete from dispatch_batch_group_relation where suite_key = ?";
    private final String sys_rdbms_156 = "insert into dispatch_group_dependency(uuid,suite_key,up_suite_key,domain_id) values(uuid(),?,?,?)";
    private final String sys_rdbms_157 = "select as_of_date from dispatch_batch_define where batch_id = ?";
    private final String sys_rdbms_158 = "insert into dispatch_batch_argument_rel(uuid,batch_id,arg_id,arg_value,domain_id) values(uuid(),?,?,?,?)";
    private final String sys_rdbms_159 = "select count(*) from dispatch_batch_argument_rel where batch_id = ? and arg_id = ?";
    private final String sys_rdbms_160 = "update dispatch_batch_argument_rel set arg_value = ? where batch_id = ? and arg_id = ?";
    private final String sys_rdbms_161 = "update dispatch_batch_define set as_of_date = ? where batch_id = ?";
    private final String sys_rdbms_162 = "select distinct uuid() as uuid,r.batch_id,d.arg_id,a.arg_value,r.domain_id,d.bind_as_of_date from dispatch_batch_group_relation r inner join dispatch_group_task_relation g on r.group_id=g.group_id inner join dispatch_task_argument_rel t on g.task_id = t.task_id inner join dispatch_argument_define d on t.arg_id = d.arg_id left join dispatch_batch_argument_rel a on a.batch_id = r.batch_id and a.arg_id = t.arg_id where d.arg_type = '4' and r.domain_id = ?";
    private final String sys_rdbms_163 = "select t.uuid,t.batch_id,t.arg_id,t.arg_value,t.domain_id from dispatch_batch_argument_rel t where t.domain_id = ? and t.batch_id = ?";
    private final String sys_rdbms_164 = "select distinct uuid() as uuid,r.batch_id,d.arg_id,a.arg_value,r.domain_id,d.bind_as_of_date from dispatch_batch_group_relation r inner join dispatch_group_task_relation g on r.group_id=g.group_id inner join dispatch_task_argument_rel t on g.task_id = t.task_id inner join dispatch_argument_define d on t.arg_id = d.arg_id left join dispatch_batch_argument_rel a on a.batch_id = r.batch_id and a.arg_id = t.arg_id where d.arg_type = '4' and r.domain_id = ? and r.batch_id = ?";
    private final String sys_rdbms_165 = "select batch_id,code_number,batch_desc,batch_status, as_of_date,domain_id,ret_msg,start_date, complete_date, end_date from dispatch_batch_define where domain_id = ? and batch_status = '1'";
    private final String sys_rdbms_166 = "delete from dispatch_batch_job_status where batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_167 = "insert into dispatch_batch_job_status(batch_id,job_id,status,suite_key,job_key,as_of_date) values(?,?,?,?,?,?)";
    private final String sys_rdbms_168 = "update dispatch_batch_job_status set status = ? where batch_id = ? and job_id = ? and as_of_date = ?";
    private final String sys_rdbms_169 = "select status from dispatch_batch_job_status t where batch_id = ? and job_id = ? and as_of_date = ?";
    private final String sys_rdbms_170 = "select count(*) from dispatch_batch_job_status t where batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_171 = "select count(*) from dispatch_batch_job_status t where batch_id = ? and status = '2' and as_of_date = ?";
    private final String sys_rdbms_172 = "delete from dispatch_batch_group_status where batch_id = ? and as_of_date = ? ";
    private final String sys_rdbms_173 = "insert into dispatch_batch_group_status(batch_id,suite_key,status,as_of_date) values(?,?,?,?)";
    private final String sys_rdbms_174 = "update dispatch_batch_group_status set status = ? where batch_id = ? and suite_key = ? and as_of_date = ?";
    private final String sys_rdbms_175 = "select status from dispatch_batch_group_status where batch_id = ? and suite_key = ? and as_of_date = ?";
    private final String sys_rdbms_176 = "update dispatch_batch_define set as_of_date = ?, batch_status = '1',start_date = now(), ret_msg = null, end_date = null where batch_id = ? and batch_status = '2'";
    private final String sys_rdbms_177 = "select count(*) from dispatch_batch_group_status where batch_id = ? and status = '2' and as_of_date = ?";
    private final String sys_rdbms_178 = "update dispatch_batch_define set batch_status = '1',start_date = now(), ret_msg = null, end_date = null where batch_id = ? and complete_date >= as_of_date";
    private final String sys_rdbms_179 = "update dispatch_batch_define set ret_msg = ? , end_date = now(), batch_status = ? where batch_id = ?";
    private final String sys_rdbms_180 = "update dispatch_batch_define set batch_status = '4' where batch_status = '1'";
    private final String sys_rdbms_181 = "select config_id,config_desc,config_value,image,details from dispatch_batch_system_config";
    private final String sys_rdbms_182 = "select t.config_value from dispatch_batch_system_config t where t.config_id = ?";
    private final String sys_rdbms_183 = "select c.config_value from dispatch_batch_domain_config c where c.domain_id = ? and c.config_id = ?";
    private final String sys_rdbms_184 = "select count(*) from dispatch_batch_domain_config where uuid = ?";
    private final String sys_rdbms_185 = "update dispatch_batch_domain_config set config_value = ? where uuid = ?";
    private final String sys_rdbms_186 = "insert into dispatch_batch_domain_config(config_id,config_value,domain_id,uuid) values(?,?,?,?)";
    private final String sys_rdbms_187 = "select config_id,config_value,domain_id from dispatch_batch_domain_config where domain_id = ?";
    private final String sys_rdbms_188 = "update dispatch_batch_group_status set start_time = now(),status = ? where suite_key = ? and batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_189 = "update dispatch_batch_group_status set end_time = now(),status = ? where suite_key = ? and batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_190 = "update dispatch_batch_job_status set start_time = now(), status = ? where batch_id = ? and job_id = ? and as_of_date = ?";
    private final String sys_rdbms_191 = "update dispatch_batch_job_status set end_time = now(), status = ? where batch_id = ? and job_id = ? and as_of_date = ?";
    private final String sys_rdbms_192 = "insert into dispatch_batch_history(sid,code_number,batch_id,batch_desc,batch_status,as_of_date,start_time,end_time,ret_msg,domain_id) select ?,code_number,batch_id,batch_desc,batch_status,as_of_date,start_date,end_date,ret_msg,domain_id from dispatch_batch_define where batch_id = ?";
    private final String sys_rdbms_193 = "select t.sid,t.code_number,t.batch_id,t.batch_desc,t.batch_status,a.batch_status_desc,t.as_of_date,t.start_time,t.end_time,t.ret_msg,t.domain_id from dispatch_batch_history t inner join dispatch_batch_status_attr a on t.batch_status = a.batch_status where t.domain_id = ?";
    private final String sys_rdbms_194 = "delete from dispatch_batch_history where sid = ?";
    private final String sys_rdbms_195 = "insert into dispatch_batch_group_history(sid,suite_key,status,start_time,end_time) select ?,suite_key,status,start_time,end_time from dispatch_batch_group_status where batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_196 = "insert into dispatch_batch_job_history(sid,job_id,status,start_time,end_time,suite_key,job_key) select ?,job_id,status,start_time,end_time,suite_key,job_key from dispatch_batch_job_status where batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_197 = "select t.sid,t.suite_key,t.status,t.start_time,t.end_time,d.batch_status_desc as status_desc,g.group_id,e.group_desc from dispatch_batch_group_history t inner join dispatch_batch_group_relation g on t.suite_key = g.suite_key inner join dispatch_group_define e on g.group_id = e.group_id left join dispatch_batch_status_attr d on t.status = d.batch_status where t.sid = ?";
    private final String sys_rdbms_198 = "select count(*) from dispatch_batch_job_history where sid = ? and suite_key = ?";
    private final String sys_rdbms_199 = "select count(*) from dispatch_batch_job_history where sid = ? and suite_key = ? and status = '2'";
    private final String sys_rdbms_200 = "select t.sid,t.job_id,t.status,t.start_time,t.end_time,t.suite_key,t.job_key,a.batch_status_desc as status_desc,td.task_id,td.task_desc,td.task_type,c.task_type_desc from dispatch_batch_job_history t left join dispatch_batch_status_attr a on t.status = a.batch_status inner join dispatch_group_task_relation o on t.job_key = o.job_key inner join dispatch_task_define td on o.task_id = td.task_id inner join dispatch_task_type_attr c on td.task_type = c.task_type where t.sid = ? and t.suite_key = ?";
    private final String sys_rdbms_201 = "select t.batch_id,t.suite_key,t.status,t.start_time,t.end_time,d.batch_status_desc as status_desc,g.group_id,e.group_desc,t.as_of_date from dispatch_batch_group_status t inner join dispatch_batch_group_relation g on t.suite_key = g.suite_key inner join dispatch_group_define e on g.group_id = e.group_id left join dispatch_batch_status_attr d on t.status = d.batch_status where t.batch_id = ? and t.as_of_date = ?";
    private final String sys_rdbms_202 = "select count(*) from dispatch_batch_job_status where batch_id = ? and suite_key = ? and as_of_date = ?";
    private final String sys_rdbms_203 = "select count(*) from dispatch_batch_job_status where batch_id = ? and suite_key = ? and status = '2' and as_of_date = ?";
    private final String sys_rdbms_204 = "select t.batch_id,t.job_id,t.status,t.start_time,t.end_time,t.suite_key,t.job_key,a.batch_status_desc as status_desc,td.task_id,td.task_desc,td.task_type,c.task_type_desc from dispatch_batch_job_status t left join dispatch_batch_status_attr a on t.status = a.batch_status inner join dispatch_group_task_relation o on t.job_key = o.job_key inner join dispatch_task_define td on o.task_id = td.task_id inner join dispatch_task_type_attr c on td.task_type = c.task_type where t.batch_id = ? and t.suite_key = ? and t.as_of_date = ?";
    private final String sys_rdbms_205 = "select t.batch_id,t.suite_key,t.status,t.start_time,t.end_time,d.batch_status_desc as status_desc,g.group_id,e.group_desc,t.as_of_date from dispatch_batch_group_status t inner join dispatch_batch_group_relation g on t.suite_key = g.suite_key inner join dispatch_group_define e on g.group_id = e.group_id left join dispatch_batch_status_attr d on t.status = d.batch_status where t.batch_id = ? and t.suite_key = ? and t.as_of_date = ?";
    private final String sys_rdbms_206 = "select t.batch_id,t.job_id,t.status,t.start_time,t.end_time,t.suite_key,t.job_key,a.batch_status_desc as status_desc,td.task_id,td.task_desc,td.task_type,c.task_type_desc from dispatch_batch_job_status t left join dispatch_batch_status_attr a on t.status = a.batch_status inner join dispatch_group_task_relation o on t.job_key = o.job_key inner join dispatch_task_define td on o.task_id = td.task_id inner join dispatch_task_type_attr c on td.task_type = c.task_type where t.batch_id = ? and t.suite_key = ? and t.job_key = ?";
    private final String sys_rdbms_210 = "select batch_status,as_of_date,complete_date, pagging_freq,pagging_freq_mult from dispatch_batch_define where batch_id = ?";
    private final String sys_rdbms_211 = "insert into dispatch_job_execute_log(job_id,message,exec_time,sort_id,batch_id,as_of_date) values(?,?,?,?,?,?)";
    private final String sys_rdbms_212 = "insert into dispatch_job_execute_history(sid,job_id,message,exec_time,sort_id,batch_id) select ?,job_id,message,exec_time,sort_id,batch_id from dispatch_job_execute_log where batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_213 = "delete from dispatch_job_execute_log where batch_id = ? and as_of_date = ?";
    private final String sys_rdbms_214 = "select job_id,message,exec_time,sort_id,sid,batch_id from dispatch_job_execute_history where job_id = ? and sid = ?";
    private final String sys_rdbms_215 = "select t.suite_key,r.job_key,d.up_job_key from dispatch_batch_group_relation t inner join dispatch_group_task_relation r on t.group_id = r.group_id inner join dispatch_task_dependency d on r.job_key = d.job_key where t.domain_id = ? and t.batch_id = ?";
    private final String sys_rdbms_216 = "select t.db as procName,t.name as procDesc,t.name as procUpId from mysql.proc t";

    @Override
    public String getSqlText(String id) throws NoSuchFieldException, IllegalAccessException {
        Field field = MySQLBatchSqlDefine.class.getDeclaredField(id);
        return field.get(this).toString();
    }
}
