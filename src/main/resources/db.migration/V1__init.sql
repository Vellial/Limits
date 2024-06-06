create table limits (id bigserial primary key, user_id bigint, limit bigint);

insert into limits(user_id, limit) values (
                                                        generate_series (1,100), 10000
                                                       )