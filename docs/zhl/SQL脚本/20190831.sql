create or replace view v_ta_jc_dev_supervise as
select
    t1.supervise_info_id ,--发牌id
    t1.business_id,--业务id
    t1.supervise_type_no,--发牌类型Code
    t1.supervise_result_no,--发牌结果Code
    t1.supervise_time,--发牌时间
    t1.state,--状态
    t1.org_id,--机构ID
    t1.promise_limit,--承诺时限
    t1.process_limit,--法定时限
    t2.supervise_type_name,--发牌类型Name
    t2.business_type,--业务类型
    t3.supervise_result_name,--发牌结果Name
    t3.supervise_level,--发牌级别
    t4.NAME as org_name,--机构名称
    (
        case when
              t1.state = 'Y'
              and (
              select count(0) from ta_jc_supervise_info tempt1 inner join ta_jc_dic_supervise_result tempt2 on tempt1.supervise_result_no = tempt2.supervise_result_code
              where  tempt1.business_id = t1.business_id
              and tempt1.supervise_type_no=t1.supervise_type_no
              and tempt1.state = 'Y'
              and tempt2.supervise_level>t3.supervise_level
          ) =0 then
          'Y'
        else
          'N'
        end
    ) isMaxSupervise--是否最高发牌
from ta_jc_supervise_info t1 inner join ta_jc_dic_supervise_type t2 on t1.supervise_type_no=t2.supervise_type_code
inner join ta_jc_dic_supervise_result t3 on t1.supervise_result_no = t3.supervise_result_code
inner join v_jc_org t4 on t1.org_id = t4.ID
where 1=1
and (
    (t2.business_type = '1' and t1.business_id in (select instance_id from ta_sp_instance inner join v_jc_approve_info on ta_sp_instance.approve_id = v_jc_approve_info.approve_id))
    or
    (t2.business_type = '2')
    or
    (t2.business_type = '3' and t1.business_id in (select consult_id from ta_sp_consult inner join v_jc_approve_info on ta_sp_consult.approve_id = v_jc_approve_info.approve_id))
)
;



create or replace view va_ta_jc_zwfw_instance_detail as
select t1.instance_id,
       t1.org_name,
       t1.instance_name,
       t1.instance_code,
       t1.apply_name,
       t1.approve_name,
       t1.query_code,
       t1.project_state,
       t1.accept_time,
       t1.end_time,
      /* t2.approve_limit    fdqx,
       t2.commitment_limit cnqx,*/
       (case when t3.promise_limit is not null then t3.promise_limit else t2.commitment_limit end) as cnqx, --承诺时限
       (case when t3.process_limit is not null then t3.process_limit else t2.approve_limit end) as cnqx, --法定时限
       t1.time_use,
       t3.supervise_result_no,
       t4.supervise_result_name
  from ta_sp_instance t1
  left join hncs_approve.ta_sp_services_guide t2 on t1.approve_id = t2.approve_id
  left join ta_jc_count_instance_info1 t5 on t1.instance_id = t5.instance_id
  left join ta_jc_supervise_info t3 on t5.supervise_id = t3.supervise_info_id
  left join ta_jc_dic_supervise_result t4 on t3.supervise_result_no = t4.supervise_result_code;
