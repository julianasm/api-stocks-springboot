create table stocks_historic_prices(
                                       id bigint primary key generated always as identity,
                                       id_stock bigint not null references stocks(id),
                                       open numeric default 0,
                                       close numeric default 0,
                                       high numeric default 0,
                                       low numeric default 0,
                                       created_on timestamp not null default current_timestamp
                                       );