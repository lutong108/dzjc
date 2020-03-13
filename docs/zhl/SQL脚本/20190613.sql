--视图
v_ta_jc_dev_supervise

create or replace trigger TRI_TA_SP_INSTANCE --创建触发器
/**
 *<p>Title:</p>
 *<p>Description:触发器实现将审批办件数据插入和更新到总库</p>
 *<p>Copyright:Copyright (c) 2019</p>
 *<p>Company:湖南科创</p>
 *@author feiwen.liu
 *@version 1.0
 *@date 2019-5-29
 *@midify by
 *@midify date
 *@midify content
 */
after insert or update --在数据库动作之后触发器执行（插入和更新操作）
--of INSTANCE_CODE,INSTANCE_NAME,ACCEPT_NAME,ACCEPT_TIME,END_NAME,END_TIME,APPLY_ID,ORG_ID,PROJECT_STATE,
--NOTACCEPT_REASON,CORRECTION_TIME,APPROVE_ID,INSTANCE_SOURCE,SUBMIT_TIME,ACCEPT_ID,END_ID,IS_SENDRESULT,
--IS_WLTS,ACCEPT_ORG_ID,APPLYER_TYPE,RESULT_TYPE
ON TA_SP_INSTANCE -- 审批实例表
FOR EACH ROW -- 每一行记录都执行
DECLARE  count_num number; -- 总库办件数
         errorcode number;
         errorinfo varchar2(200);
         is_sussess number;
BEGIN
    --非暂存、并联暂存、并联分发的数据才能进入总库
    IF(:NEW.PROJECT_STATE NOT IN ('0','20','21')) THEN

        SELECT COUNT(1) INTO count_num FROM SUPERVISE.TA_SP_INSTANCE I WHERE I.INSTANCE_ID = :NEW.INSTANCE_ID;

        IF(count_num=0) THEN
            INSERT INTO SUPERVISE.TA_SP_INSTANCE(INSTANCE_ID,INSTANCE_CODE,APPROVE_CODE,INSTANCE_NAME,INSTANCE_PY,ACCEPT_NAME,
            ACCEPT_TIME,END_NAME,END_TIME,APPLY_ID,APPLY_TYPE,APPLY_NAME,ORG_ID,AREA_CODE,PROJECT_STATE,QUEUE_CODE,QUERY_CODE,
            NOTACCEPT_REASON,CORRECTION_TIME,APPROVE_ID,INSTANCE_SOURCE,SUBMIT_TIME,REMARK,PRE_REASON,APPROVE_NAME,ACCEPT_ID,END_ID,
            ORG_NAME,CUR_TACHE,IS_SENDRESULT,RZ_APPLY_ID,IS_WLTS,TRANSACTOR_ID,IS_EXCHANGE,
            ACCEPT_ORG_ID,ACCEPT_AREA_CODE,IS_HELPAGENCY,MOD_FLAG,PRETRIAL_TIME,BATCH_ID,DUE_TIME,
            ORG_SOCIAL_CREDIT_NO,APPLYER_TYPE,RESULT_TYPE,RIGHTS_CODE,TASKHANDLEITEM,TRANS_TYPE,TRANSACTION_FROM,LOCALCATALOGCODE,
            LOCALTASKCODE,VERSION_NUM,SJDJ_IS_DISTRIBUTE,
            SJDJ_DISTRIBUTE_MODE,SJDJ_DISTRIBUTE_ACCOUNT)
            SELECT :NEW.INSTANCE_ID,:NEW.INSTANCE_CODE,:NEW.APPROVE_CODE,:NEW.INSTANCE_NAME,:NEW.INSTANCE_PY,:NEW.ACCEPT_NAME,
            :NEW.ACCEPT_TIME,:NEW.END_NAME,:NEW.END_TIME,:NEW.APPLY_ID,:NEW.APPLY_TYPE,:NEW.APPLY_NAME,:NEW.ORG_ID,:NEW.AREA_CODE,:NEW.PROJECT_STATE,:NEW.QUEUE_CODE,:NEW.QUERY_CODE,
            :NEW.NOTACCEPT_REASON,:NEW.CORRECTION_TIME,:NEW.APPROVE_ID,:NEW.INSTANCE_SOURCE,:NEW.SUBMIT_TIME,:NEW.REMARK,:NEW.PRE_REASON,:NEW.APPROVE_NAME,:NEW.ACCEPT_ID,:NEW.END_ID,
            :NEW.ORG_NAME,:NEW.CUR_TACHE,:NEW.IS_SENDRESULT,:NEW.RZ_APPLY_ID,:NEW.IS_WLTS,:NEW.TRANSACTOR_ID,'N',
            :NEW.ACCEPT_ORG_ID,:NEW.ACCEPT_AREA_CODE,:NEW.IS_HELPAGENCY,'N',:NEW.PRETRIAL_TIME,:NEW.BATCH_ID,:NEW.DUE_TIME,
            :NEW.ORG_SOCIAL_CREDIT_NO,:NEW.APPLYER_TYPE,:NEW.RESULT_TYPE,:NEW.RIGHTS_CODE,:NEW.TASKHANDLEITEM,:NEW.TRANS_TYPE,:NEW.TRANSACTION_FROM,:NEW.LOCALCATALOGCODE,
            :NEW.LOCALTASKCODE,:NEW.VERSION_NUM,DECODE(A.IS_DISTRIBUTE,NULL,'Y',A.IS_DISTRIBUTE) SJDJ_IS_DISTRIBUTE,
            CASE WHEN A.IS_DISTRIBUTE IS NULL THEN '1' ELSE A.DISTRIBUTE_MODE END SJDJ_DISTRIBUTE_MODE,A.DISTRIBUTE_ACCOUNT
            FROM DUAL LEFT JOIN TA_SP_DOCKING_APPROVE A ON A.APPROVE_ID = :NEW.APPROVE_ID;

        ELSE

            UPDATE SUPERVISE.TA_SP_INSTANCE SET (INSTANCE_CODE,APPROVE_CODE,INSTANCE_NAME,INSTANCE_PY,ACCEPT_NAME,
            ACCEPT_TIME,END_NAME,END_TIME,APPLY_ID,APPLY_TYPE,APPLY_NAME,ORG_ID,AREA_CODE,PROJECT_STATE,QUEUE_CODE,QUERY_CODE,
            NOTACCEPT_REASON,CORRECTION_TIME,APPROVE_ID,INSTANCE_SOURCE,SUBMIT_TIME,REMARK,PRE_REASON,APPROVE_NAME,ACCEPT_ID,END_ID,
            ORG_NAME,CUR_TACHE,IS_SENDRESULT,RZ_APPLY_ID,IS_WLTS,TRANSACTOR_ID,IS_EXCHANGE,
            ACCEPT_ORG_ID,ACCEPT_AREA_CODE,IS_HELPAGENCY,MOD_FLAG,PRETRIAL_TIME,BATCH_ID,DUE_TIME,
            ORG_SOCIAL_CREDIT_NO,APPLYER_TYPE,RESULT_TYPE,RIGHTS_CODE,TASKHANDLEITEM,TRANS_TYPE,TRANSACTION_FROM,LOCALCATALOGCODE,
            LOCALTASKCODE,VERSION_NUM)
            =(SELECT :NEW.INSTANCE_CODE,:NEW.APPROVE_CODE,:NEW.INSTANCE_NAME,:NEW.INSTANCE_PY,:NEW.ACCEPT_NAME,
            :NEW.ACCEPT_TIME,:NEW.END_NAME,:NEW.END_TIME,:NEW.APPLY_ID,:NEW.APPLY_TYPE,:NEW.APPLY_NAME,:NEW.ORG_ID,:NEW.AREA_CODE,:NEW.PROJECT_STATE,:NEW.QUEUE_CODE,:NEW.QUERY_CODE,
            :NEW.NOTACCEPT_REASON,:NEW.CORRECTION_TIME,:NEW.APPROVE_ID,:NEW.INSTANCE_SOURCE,:NEW.SUBMIT_TIME,:NEW.REMARK,:NEW.PRE_REASON,:NEW.APPROVE_NAME,:NEW.ACCEPT_ID,:NEW.END_ID,
            :NEW.ORG_NAME,:NEW.CUR_TACHE,:NEW.IS_SENDRESULT,:NEW.RZ_APPLY_ID,:NEW.IS_WLTS,:NEW.TRANSACTOR_ID,'N',
            :NEW.ACCEPT_ORG_ID,:NEW.ACCEPT_AREA_CODE,:NEW.IS_HELPAGENCY,'N',:NEW.PRETRIAL_TIME,:NEW.BATCH_ID,:NEW.DUE_TIME,
            :NEW.ORG_SOCIAL_CREDIT_NO,:NEW.APPLYER_TYPE,:NEW.RESULT_TYPE,:NEW.RIGHTS_CODE,:NEW.TASKHANDLEITEM,:NEW.TRANS_TYPE,:NEW.TRANSACTION_FROM,:NEW.LOCALCATALOGCODE,
            :NEW.LOCALTASKCODE,:NEW.VERSION_NUM
            FROM DUAL)
            WHERE INSTANCE_ID = :NEW.INSTANCE_ID;


        END IF;
    END IF;
  --异常处理，将错误日志插入错误日志表中
  exception
  when others then
  errorcode := SQLCODE; -- ORA 错误日志号
  errorinfo := SQLERRM; -- ORA 错误详细
  insert into TA_TIR_DATA_EXCEPTION(errcode,errmsg,errtir,tabkey)
  values(errorcode,errorinfo,'TRI_TA_SP_INSTANCE',:NEW.INSTANCE_ID);

END TRI_TA_SP_INSTANCE;


--================================================================================================================


create or replace trigger tri_ta_sp_consult --创建触发器
/**
 *<p>Title:</p>
 *<p>Description:触发器实现将咨询表数据插入和更新到总库</p>
 *<p>Copyright:Copyright (c) 2019</p>
 *<p>Company:湖南科创</p>
 *@author hailin.zou
 *@version 1.0
 *@date 2019-6-13
 *@midify by
 *@midify date
 *@midify content
 */
after insert or update --在数据库动作之后触发器执行（插入和更新操作）
on ta_sp_consult -- 咨询表
for each row -- 每一行记录都执行
declare  count_num number; -- 总库审批意见数
         errorcode number;
         errorinfo varchar2(200);
begin

        select count(1) into count_num from supervise.ta_sp_consult i where i.consult_id = :new.consult_id;

        IF(count_num=0) THEN

insert into supervise.ta_sp_consult(
consult_id, consult_title, org_id, org_name, apply_id, apply_type, apply_name, approve_id, consult_time, consult_content, is_open, reply_org, reply_time, reply_content, consult_source, area_code, reply_name, apply_phone, approve_name, consult_limit, expire_time, query_code, is_valid, certificate_num, etldown_time, etl_time, is_exchange,sjdj_is_distribute,sjdj_distribute_mode,sjdj_distribute_account
)
select
:new.consult_id, :new.consult_title, :new.org_id, :new.org_name, :new.apply_id, :new.apply_type, :new.apply_name, :new.approve_id, :new.consult_time, :new.consult_content, :new.is_open, :new.reply_org, :new.reply_time, :new.reply_content, :new.consult_source, :new.area_code, :new.reply_name, :new.apply_phone, :new.approve_name, :new.consult_limit, :new.expire_time, :new.query_code, :new.is_valid, :new.certificate_num, :new.etldown_time, :new.etl_time, 'N',
decode(a.is_distribute,null,'Y',a.is_distribute) sjdj_is_distribute,
(case when a.is_distribute is null then '1' else a.distribute_mode end) sjdj_distribute_mode,
a.distribute_account
from dual left join ta_sp_docking_approve a on a.approve_id = :new.approve_id;

        else

update supervise.ta_sp_consult set (
consult_id, consult_title, org_id, org_name, apply_id, apply_type, apply_name, approve_id, consult_time, consult_content, is_open, reply_org, reply_time, reply_content, consult_source, area_code, reply_name, apply_phone, approve_name, consult_limit, expire_time, query_code, is_valid, certificate_num, etldown_time, etl_time, is_exchange
)
=(select
:new.consult_id, :new.consult_title, :new.org_id, :new.org_name, :new.apply_id, :new.apply_type, :new.apply_name, :new.approve_id, :new.consult_time, :new.consult_content, :new.is_open, :new.reply_org, :new.reply_time, :new.reply_content, :new.consult_source, :new.area_code, :new.reply_name, :new.apply_phone, :new.approve_name, :new.consult_limit, :new.expire_time, :new.query_code, :new.is_valid, :new.certificate_num, :new.etldown_time, :new.etl_time,'N'
from dual)
where consult_id = :new.consult_id;

        end if;
  --异常处理，将错误日志插入错误日志表中
  exception
  when others then
  errorcode := sqlcode; -- ora 错误日志号
  errorinfo := sqlerrm; -- ora 错误详细
  insert into ta_tir_data_exception(errcode,errmsg,errtir,tabkey)
  values(errorcode,errorinfo,'tri_ta_sp_consult',:new.consult_id);

end tri_ta_sp_consult;
