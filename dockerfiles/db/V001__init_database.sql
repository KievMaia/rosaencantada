/*==============================================================*/
/* v001__init_database                      */
/*==============================================================*/
do
$do$
begin
  create user "postgres" with encrypted password 'postgres';
  exception
    when duplicate_object then
      raise notice 'Role "postgres" already exists';
end
$do$;

\connect rosaencantada;

create schema if not exists rosaencantada;

grant all privileges on database "rosaencantada" to "postgres";
grant usage on schema rosaencantada to "postgres";