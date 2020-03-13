create or replace view va_ta_jc_zwfw_instance_pagelist as
select
       t1.instance_id as instance_id,
       t1.area_org_name as area_org_name,
       t1.org_code,
       t1.instance_name as instance_name,
       t1.instance_code as instance_code,
       t1.accept_time as accept_time,
       t1.finish_time as finish_time,
       t1.processing_state as processing_state,
       t1.org_id as org_id,
       t1.approve_name as approve_name,
       t1.approve_id as approve_id,
       t1.supervise_result_no,
       t1.type_code as approve_type_code
  from
  (
      select c.instance_id as instance_id,
             d.supervise_result_no as supervise_result_no,
             c.instance_name as instance_name,
             c.project_state as processing_state,
             c.accept_time as accept_time,
             c.end_time as finish_time,
             b.id as org_id,
             b.name as area_org_name,
             b.code as org_code,
             c.approve_name as approve_name,
             c.instance_code as instance_code,
             c.approve_id,
             a.type_code
        from ta_sp_instance c
       inner join v_jc_approve_info a on c.approve_id = a.approve_id
       inner join v_org b on c.org_id = b.id
       left join (
        select max(supervise_result_no) as supervise_result_no, f.business_id from ta_jc_supervise_info f
         where 1 = 1 --and f.business_id = c.instance_id
           and f.state = 'Y'
           and f.supervise_type_no = '1011'
         group by f.business_id
       ) d on d.business_id = c.instance_id
       where c.accept_time >= to_date('2015-04-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss')
  ) t1;
