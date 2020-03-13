-- Create table
create table td_jc_filter
(
  id          varchar2(50),
  type        varchar2(50),
  business_id varchar2(100)
)
;
-- Add comments to the table 
comment on table td_jc_filter
  is '字典数据过滤';
-- Add comments to the columns 
comment on column td_jc_filter.id
  is '主键';
comment on column td_jc_filter.type
  is '类型';
comment on column td_jc_filter.business_id
  is '业务id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table td_jc_filter
  add constraint pk_td_jc_filter primary key (ID);

insert into td_jc_filter
  (id, type, business_id)
values
  (sys_guid(), 'td_sm_dictdata', 'E0D893CC57184BC8B859AAC06B9FFF09');
commit;