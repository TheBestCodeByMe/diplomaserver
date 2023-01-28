CREATE ROLE dev_sss_store_admin WITH LOGIN CREATEDB ENCRYPTED PASSWORD 'fakedPassword';

create sequence seq_action_id;

alter sequence seq_action_id owner to dev_sss_store_admin;

create domain tbanknotesamt as integer;

alter domain tbanknotesamt owner to dev_sss_store_admin;

create domain tbinary as bytea;

alter domain tbinary owner to dev_sss_store_admin;

create domain tboolean as boolean;

alter domain tboolean owner to dev_sss_store_admin;

create domain tchessgamescore as numeric(3, 1);

alter domain tchessgamescore owner to dev_sss_store_admin;

create domain tcoinsamt as integer;

alter domain tcoinsamt owner to dev_sss_store_admin;

create domain tcurrencyiso as varchar(3);

alter domain tcurrencyiso owner to dev_sss_store_admin;

create domain tcurrencystr10 as varchar(10);

alter domain tcurrencystr10 owner to dev_sss_store_admin;

create domain tdate as date;

alter domain tdate owner to dev_sss_store_admin;

create domain tdatetime as timestamp;

alter domain tdatetime owner to dev_sss_store_admin;

create domain tgpscoordinates as point;

alter domain tgpscoordinates owner to dev_sss_store_admin;

create domain tidbigcode as bigint;

alter domain tidbigcode owner to dev_sss_store_admin;

create domain tidcode as integer;

comment on type tidcode is 'Код сущности';

alter domain tidcode owner to dev_sss_store_admin;

create domain tiduser as integer;

alter domain tiduser owner to dev_sss_store_admin;

create domain timage as bytea;

alter domain timage owner to dev_sss_store_admin;

create domain tintcounter as integer;

alter domain tintcounter owner to dev_sss_store_admin;

create domain tinteger as integer;

alter domain tinteger owner to dev_sss_store_admin;

create domain titemtype as smallint;

alter domain titemtype owner to dev_sss_store_admin;

create domain tmoney as numeric(22, 4);

alter domain tmoney owner to dev_sss_store_admin;

create domain tpercrate as numeric(12, 6);

alter domain tpercrate owner to dev_sss_store_admin;

create domain tpercrateext as numeric(16, 8);

alter domain tpercrateext owner to dev_sss_store_admin;

create domain treal as real;

alter domain treal owner to dev_sss_store_admin;

create domain tstr10 as varchar(10);

alter domain tstr10 owner to dev_sss_store_admin;

create domain tstr100 as varchar(100);

alter domain tstr100 owner to dev_sss_store_admin;

create domain tstr10000 as varchar(10000);

alter domain tstr10000 owner to dev_sss_store_admin;

create domain tstr128 as varchar(128);

alter domain tstr128 owner to dev_sss_store_admin;

create domain tstr2 as varchar(2);

alter domain tstr2 owner to dev_sss_store_admin;

create domain tstr20 as varchar(20);

alter domain tstr20 owner to dev_sss_store_admin;

create domain tstr200 as varchar(200);

alter domain tstr200 owner to dev_sss_store_admin;

create domain tstr2000 as varchar(2000);

alter domain tstr2000 owner to dev_sss_store_admin;

create domain tstr3 as varchar(3);

alter domain tstr3 owner to dev_sss_store_admin;

create domain tstr30 as varchar(30);

alter domain tstr30 owner to dev_sss_store_admin;

create domain tstr40 as varchar(40);

alter domain tstr40 owner to dev_sss_store_admin;

create domain tstr400 as varchar(400);

alter domain tstr400 owner to dev_sss_store_admin;

create domain tstr50 as varchar(50);

alter domain tstr50 owner to dev_sss_store_admin;

create domain tstr80 as varchar(80);

alter domain tstr80 owner to dev_sss_store_admin;

create domain tsumext as numeric(24, 4);

alter domain tsumext owner to dev_sss_store_admin;

create domain ttext as text;

alter domain ttext owner to dev_sss_store_admin;

create domain ttime as time;

alter domain ttime owner to dev_sss_store_admin;

create domain tidbytecode as numeric(1, 0);

alter domain tidbytecode owner to dev_sss_store_admin;

create table core_action_codes_ref
(
    action_code tidcode  not null
        constraint pk_core_action_codes_ref
            primary key,
    action_name tstr80,
    app_name    tstr100  not null,
    is_closed   tboolean not null
);

comment on table core_action_codes_ref is 'Справочник зарегистрированных регистрированных действий';

alter table core_action_codes_ref
    owner to dev_sss_store_admin;

create table core_entity_types_ref
(
    entity_type_id   tidcode not null
        constraint pk_core_entity_types_ref
            primary key,
    entity_type_name tstr100 not null,
    entity_app_name  tstr100 not null
);

comment on table core_entity_types_ref is 'Cправочник сущностей';

alter table core_entity_types_ref
    owner to dev_sss_store_admin;

create table core_entity_kinds_ref
(
    entity_kind_id   tidcode not null
        constraint pk_core_entity_kinds_ref
            primary key,
    entity_type_id   tidcode not null
        constraint fk_core_ent_entity_ki_core_ent
            references core_entity_types_ref
            on update restrict on delete restrict,
    entity_kind_name tstr100 not null
);

comment on table core_entity_kinds_ref is 'Cправочник видов сущностей';

alter table core_entity_kinds_ref
    owner to dev_sss_store_admin;

create table core_entity_statuses_ref
(
    entity_status_id   tidcode not null
        constraint pk_core_r2dbc_entity_statuses_ref
            primary key,
    entity_type_id     tidcode not null
        constraint fk_core_ent_entity_ty_core_ent
            references core_entity_types_ref
            on update restrict on delete restrict,
    entity_status_name tstr30  not null
);

comment on table core_entity_statuses_ref is 'Справочник статусов ';

alter table core_entity_statuses_ref
    owner to dev_sss_store_admin;

create table core_entities
(
    entity_id        tidbigcode not null
        constraint pk_core_entities
            primary key,
    entity_type_id   tidcode    not null
        constraint fk_core_ent_entity_ty_core_ent
            references core_entity_types_ref
            on update restrict on delete restrict,
    entity_status_id tidcode    not null
        constraint fk_core_entities_status_id
            references core_entity_statuses_ref,
    create_date      tdate      not null,
    close_date       tdatetime,
    modify_date      tdatetime
);

comment on table core_entities is 'Картотека сущностей';

alter table core_entities
    owner to dev_sss_store_admin;

create table core_actions
(
    action_id       tidbigcode not null
        constraint pk_core_actions
            primary key,
    entity_id       tidbigcode not null
        constraint fk_actions_entity_id
            references core_entities
            on update restrict on delete restrict,
    action_code     tidcode    not null
        constraint fk_ta_actrefid
            references core_action_codes_ref
            on update restrict on delete restrict,
    user_id         tidbigcode not null,
    execute_date    tdatetime  not null,
    action_address  tstr40     not null,
    err_msg         ttext,
    action_duration ttime,
    notes           ttext
);

comment on table core_actions is 'Картотека выполненный действий пользователя
';

alter table core_actions
    owner to dev_sss_store_admin;

create table st_customers
(
    customer_id  tidbigcode not null
        constraint pk_st_customers
            primary key
        constraint fk_st_customers_customer_id
            references core_entities,
    access_token tstr50     not null
        constraint ak_st_customers
            unique
);

alter table st_customers
    owner to dev_sss_store_admin;

create table st_vendors
(
    vendor_id             tidbigcode not null
        constraint pk_vendors
            primary key
        constraint fk_st_vendors_vendor_id
            references core_entities,
    vendor_access_token   tstr50     not null,
    vendor_login          tstr100    not null
        constraint ak_vendors_login
            unique,
    vendor_password       tstr400    not null,
    vendor_first_name     tstr200    not null,
    vendor_last_name      tstr200    not null,
    vendor_address1       tstr200,
    vendor_address2       tstr200,
    vendor_address3       tstr200,
    vendor_address4       tstr200,
    vendor_country        tstr2      not null,
    vendor_served_regions tstr400,
    vendor_served_cities  tstr400,
    email                 tstr100,
    mobile_phone1         tstr100,
    mobile_phone2         tstr100,
    fax1                  tstr100,
    fax2                  tstr100,
    can_update_catalog    tboolean   not null
);

alter table st_vendors
    owner to dev_sss_store_admin;

create table st_product_category_ref
(
    product_category_id        tinteger not null
        constraint pk_st_product_category_ref
            primary key,
    product_category_code      tstr50   not null
        constraint ak_st_product_category_ref
            unique,
    product_category_name      tstr200  not null,
    parent_product_category_id tidcode
);

alter table st_product_category_ref
    owner to dev_sss_store_admin;

create table st_products
(
    product_id          tidbigcode not null
        constraint pk_st_products
            primary key
        constraint fk_st_products_product_id
            references core_entities,
    product_category_id tinteger   not null
        constraint pk_st_products_product_category_id
            references st_product_category_ref,
    product_full_name   tstr400    not null
        constraint ak_product_full_name_ref
            unique,
    product_description tstr400,
    product_sku         tstr100    not null,
    product_short_name  tstr100,
    product_short_code  tstr50     not null
        constraint ak_st_products_short_code
            unique
);

alter table st_products
    owner to dev_sss_store_admin;

create table st_vendors_hist
(
    vendor_id             tidbigcode not null
        constraint fk_st_vendors_vendor_id
            references core_entities,
    actual_date           tdatetime  not null,
    vendor_access_token   tstr50,
    vendor_login          tstr100,
    vendor_password       tstr400,
    vendor_first_name     tstr200,
    vendor_last_name      tstr200,
    vendor_address1       tstr200,
    vendor_address2       tstr200,
    vendor_address3       tstr200,
    vendor_address4       tstr200,
    vendor_country        tstr2,
    vendor_served_regions tstr400,
    vendor_served_cities  tstr400,
    email                 tstr100,
    mobile_phone1         tstr100,
    mobile_phone2         tstr100,
    fax1                  tstr100,
    fax2                  tstr100,
    can_update_catalog    tboolean
);

alter table st_vendors_hist
    owner to dev_sss_store_admin;

create table st_product_prices
(
    price_id            tidbigcode   not null
        constraint pk_product_prices
            primary key
        constraint fk_st_product_prices_price_id
            references core_entities,
    vendor_id           tidbigcode   not null
        constraint fk_st_product_proce_vendor_id
            references st_vendors,
    product_id          tidbigcode   not null
        constraint fk_st_product_prices_product_id
            references st_products,
    price_note          tstr400,
    actual_date_from    tdatetime,
    actual_date_to      tdatetime,
    price_amt           tmoney       not null,
    price_quantity      tidcode      not null,
    price_currency      tcurrencyiso not null,
    show_rest_on_ui     tboolean     not null,
    initial_rest_amount tinteger     not null
);

alter table st_product_prices
    owner to dev_sss_store_admin;

create table st_product_prices_hist
(
    price_id            tidbigcode not null,
    actual_date         tdatetime  not null,
    vendor_id           tidbigcode,
    product_id          tidbigcode,
    price_note          tstr400,
    actual_date_from    tdatetime,
    actual_date_to      tdatetime,
    price_amt           tmoney,
    price_quantity      tidcode,
    price_currency      tcurrencyiso,
    show_rest_on_ui     tboolean,
    initial_rest_amount tinteger
);

alter table st_product_prices_hist
    owner to dev_sss_store_admin;

create table st_products_rest
(
    price_id  tidbigcode not null
        constraint fk_products_rest_price_id
            references st_product_prices,
    rest_amt  tinteger   not null,
    rest_date tdatetime  not null,
    rest_id   tidbigcode not null
        constraint pk_st_products_rest
            primary key
);

comment on table st_products_rest is 'product rests';

alter table st_products_rest
    owner to dev_sss_store_admin;

create table st_vendor_accounts
(
    account_id    tidbigcode not null
        constraint pk_vendor_accounts
            primary key,
    bank          tstr100    not null,
    account       tstr100    not null
        constraint ak_vendor_accounts
            unique,
    currency      tstr3      not null,
    bic           tstr100    not null,
    correspondent tstr100    not null,
    swift         tstr50,
    tin           tstr50,
    vendor_id     tidbigcode not null
);

alter table st_vendor_accounts
    owner to dev_sss_store_admin;

create table st_vendor_accounts_hist
(
    account_id    tidbigcode not null,
    actual_date   tdatetime  not null,
    bank          tstr100,
    account       tstr100,
    currency      tstr3,
    bic           tstr100,
    correspondent tstr100,
    swift         tstr50,
    tin           tstr50,
    vendor_id     tidbigcode
);

alter table st_vendor_accounts_hist
    owner to dev_sss_store_admin;

create table st_offices
(
    office_id        tidbigcode not null
        constraint fk_offices_st
            primary key
        constraint fk_st_offices_office_id
            references core_entities,
    office_code      tstr100    not null
        constraint ak_offices_st
            unique,
    office_country   tstr2      not null,
    office_name      tstr200    not null,
    office_region    tstr40     not null,
    office_address   tstr200,
    office_note      tstr400,
    parent_office_id tidbigcode
        constraint fk_offices_parent_office
            references st_offices
);

comment on table st_offices is 'Store offices';

alter table st_offices
    owner to dev_sss_store_admin;

create table st_offices_hist
(
    office_id        tidbigcode not null,
    actual_date      tdatetime  not null,
    office_code      tstr100,
    office_country   tstr2,
    office_name      tstr200,
    office_region    tstr40,
    office_address   tstr200,
    office_note      tstr400,
    parent_office_id tidbigcode
);

comment on table st_offices_hist is 'Store offices hist';

alter table st_offices_hist
    owner to dev_sss_store_admin;

create table st_departments
(
    department_id        tidbigcode not null
        constraint st_departments_pk
            primary key
        constraint fk_departments_department_id
            references core_entities,
    department_code      tstr100    not null
        constraint st_departments_ak
            unique,
    department_name      tstr200    not null,
    department_note      tstr400,
    parent_department_id tidbigcode
);

comment on table st_departments is 'Manager departments';

alter table st_departments
    owner to dev_sss_store_admin;

create table st_departments_hist
(
    department_id        tidbigcode not null,
    actual_date          tdatetime  not null,
    department_code      tstr100,
    department_name      tstr200,
    department_note      tstr400,
    parent_department_id tidbigcode
);

comment on table st_departments_hist is 'Manager departments history';

alter table st_departments_hist
    owner to dev_sss_store_admin;

create table st_posts
(
    post_id   tidbigcode not null
        constraint fk_posts_st
            primary key
        constraint fk_st_posts_post_id
            references core_entities,
    post_code tstr100    not null
        constraint ak_posts_st
            unique,
    post_name tstr200    not null,
    post_note tstr400
);

comment on table st_posts is 'Store posts';

alter table st_posts
    owner to dev_sss_store_admin;

create table st_posts_hist
(
    post_id     tidbigcode,
    actual_date tdatetime,
    post_code   tstr100,
    post_name   tstr200,
    post_note   tstr400
);

comment on table st_posts_hist is 'Store posts hist';

alter table st_posts_hist
    owner to dev_sss_store_admin;

create table st_managers
(
    manager_id         tidbigcode not null
        constraint pk_managers_st
            primary key
        constraint fk_st_managers_manager_id
            references core_entities,
    manager_login      tstr100    not null
        constraint ak_managers_st_login
            unique,
    post_id            tidbigcode not null
        constraint fk_managers_post_id
            references st_posts,
    office_id          tidbigcode not null
        constraint fk_managers_office_id
            references st_offices,
    department_id      tidbigcode not null
        constraint fk_managers_department_id
            references st_departments,
    gender             tstr2,
    manager_first_name tstr200    not null,
    manager_last_name  tstr200    not null,
    manager_brith_date tdate,
    manager_email      tstr100,
    manager_phone_1    tstr100,
    manager_phone_2    tstr100,
    manager_country    tstr2,
    manager_region     tstr40,
    parent_manager_id  tidbigcode
        constraint fk_managers_parent_manager_id
            references st_managers,
    manager_password   tstr200
);

comment on table st_managers is 'Store managers';

alter table st_managers
    owner to dev_sss_store_admin;

create table st_managers_hist
(
    manager_id         tidbigcode,
    actual_date        tdatetime,
    manager_login      tstr100,
    post_id            tidbigcode,
    office_id          tidbigcode,
    department_id      tidbigcode,
    gender             tstr2,
    manager_first_name tstr200,
    manager_last_name  tstr200,
    manager_brith_date tdate,
    manager_email      tstr100,
    manager_phone_1    tstr100,
    manager_phone_2    tstr100,
    manager_country    tstr2,
    manager_region     tstr40,
    parent_manager_id  tidbigcode,
    manager_password   tstr200
);

comment on table st_managers_hist is 'Store managers hist';

alter table st_managers_hist
    owner to dev_sss_store_admin;

create table st_service_groups
(
    service_group_id        tidbigcode not null
        constraint pk_st_service_groups
            primary key,
    service_group_name      tstr400    not null,
    region                  tstr100    not null,
    parent_service_group_id tidbigcode
        constraint fk_service_groups_parent_group_id
            references st_service_groups,
    service_group_code      tstr100    not null
        constraint ak_st_service_groups
            unique,
    service_group_note      tstr200
);

alter table st_service_groups
    owner to dev_sss_store_admin;

create table st_service_groups_hist
(
    service_group_id        tidbigcode not null,
    actual_date             tdatetime  not null,
    service_group_name      tstr400,
    region                  tstr100,
    parent_service_group_id tidbigcode,
    service_group_code      tstr100,
    service_group_note      tstr200
);

alter table st_service_groups_hist
    owner to dev_sss_store_admin;

create table st_issue_points
(
    issue_point_id            tidbigcode not null
        constraint fk_st_issue_point
            primary key
        constraint fk_st_issue_points_point_id
            references core_entities,
    issue_point_code          tstr100    not null
        constraint ak_st_issue_points
            unique,
    issue_point_region        tstr40     not null,
    issue_point_name          tstr200    not null,
    issue_point_working_hours tstr200    not null,
    issue_point_phones        tstr200    not null,
    issue_point_contact_faces tstr400,
    issue_point_mail          tstr200,
    issue_point_address       tstr200,
    issue_point_note          tstr400
);

comment on table st_issue_points is 'Store issue points';

alter table st_issue_points
    owner to dev_sss_store_admin;

create table st_issue_points_hist
(
    issue_point_id            tidbigcode not null,
    actual_date               tdatetime  not null,
    issue_point_code          tstr100,
    issue_point_region        tstr40,
    issue_point_name          tstr200,
    issue_point_working_hours tstr200,
    issue_point_phones        tstr200,
    issue_point_contact_faces tstr400,
    issue_point_mail          tstr200,
    issue_point_address       tstr200,
    issue_point_note          tstr400
);

comment on table st_issue_points_hist is 'Store issue points hist';

alter table st_issue_points_hist
    owner to dev_sss_store_admin;

create table st_delivery_points_hist
(
    delivery_point_id            tidbigcode not null,
    actual_date                  tdatetime  not null,
    customer_id                  tidbigcode,
    delivery_point_code          tstr100,
    delivery_point_region        tstr40,
    delivery_point_contact_faces tstr400,
    delivery_point_mail          tstr200,
    delivery_point_address       tstr200    not null,
    delivery_point_note          tstr400
);

comment on table st_delivery_points_hist is 'Delivery points hist';

alter table st_delivery_points_hist
    owner to dev_sss_store_admin;

create table st_delivery_points
(
    delivery_point_id            tidbigcode not null
        constraint fk_st_delivery_point
            primary key
        constraint fk_st_delivery_points_point_id
            references core_entities,
    delivery_point_code          tstr100    not null
        constraint ak_st_delivery_points
            unique,
    customer_id                  tidbigcode not null
        constraint fk_st_delivery_points_customer_id
            references st_customers,
    delivery_point_region        tstr40,
    delivery_point_contact_faces tstr400,
    delivery_point_mail          tstr200,
    delivery_point_address       tstr200    not null,
    delivery_point_note          tstr400
);

comment on table st_delivery_points is 'Delivery points ';

alter table st_delivery_points
    owner to dev_sss_store_admin;

create table st_products_hist
(
    product_id          tidbigcode not null
        constraint fk_st_products_hist_product_id
            references st_products,
    actual_date         tdatetime,
    product_category_id tinteger,
    product_full_name   tstr400,
    product_description tstr400,
    product_short_name  tstr100,
    product_short_code  tstr50,
    product_sku         tstr100
);

alter table st_products_hist
    owner to dev_sss_store_admin;

create table st_orders
(
    order_id          tidbigcode   not null
        constraint fk_st_orders
            primary key
        constraint fk_st_orders_order_id
            references core_entities,
    order_kind_id     tidcode      not null
        constraint fk_st_orders_kind
            references core_entity_kinds_ref,
    order_num         tstr100      not null
        constraint ak_st_orders_order_num
            unique,
    customer_id       tidbigcode   not null
        constraint fk_st_orders_customer_id
            references st_customers,
    issue_point_id    tidbigcode
        constraint fk_st_orders_issue_point_id
            references st_issue_points,
    delivery_point_id tidbigcode
        constraint fk_st_orders_delivery_point_id
            references st_delivery_points,
    term_date         tdatetime    not null,
    delivery_time     tstr100,
    order_sum         tmoney       not null,
    order_currency    tcurrencyiso not null,
    recharge_sum      tmoney,
    delivery_sum      tmoney,
    order_note        tstr400
);

comment on table st_orders is 'Customers orders ';

alter table st_orders
    owner to dev_sss_store_admin;

create table st_orders_items
(
    order_item_id            tidbigcode   not null
        constraint fk_st_orders_items
            primary key
        constraint fk_st_orders_items_order_item_id
            references core_entities,
    order_item_num           tstr100      not null
        constraint ak_st_orders_items_order_num
            unique,
    order_id                 tidbigcode   not null
        constraint fk_st_orders_items_order_id
            references st_orders,
    product_id               tidbigcode   not null
        constraint fk_st_orders_items_product_id
            references st_products,
    vendor_id                tidbigcode   not null
        constraint fk_st_orders_vendor_id
            references st_vendors,
    order_item_sum           tmoney       not null,
    order_item_price         tmoney       not null,
    order_item_amount        tinteger     not null,
    order_item_currency      tcurrencyiso not null,
    order_delivery_sum       tmoney,
    order_item_delivery_note tstr400,
    order_recharge_sum       tmoney,
    order_recharge_date      tdatetime,
    order_recharge_reason    tstr400
);

comment on table st_orders_items is 'Customers orders items';

alter table st_orders_items
    owner to dev_sss_store_admin;

create table st_invoices
(
    invoice_id         tidbigcode not null
        constraint pk_st_invoices
            primary key,
    invoice_kind_id    tinteger   not null
        constraint fk_st_invoices_kind_id
            references core_entity_kinds_ref,
    order_id           tidbigcode not null
        constraint fk_st_invoices_order_id
            references st_orders,
    invoice_number     tstr50     not null
        constraint ak_invoices_invoice_number
            unique,
    total_amount       tmoney     not null,
    supplier_name      tstr200    not null,
    supplier_address   tstr200,
    supplier_email     tstr100,
    reciever_name      tstr200    not null,
    reciever_address   tstr400,
    reciever_email     tstr100,
    subtotal_amount    tmoney     not null,
    vat_amount         tmoney,
    tax_sum            tmoney     not null,
    discount_sum       tmoney,
    transactions_sum   tmoney,
    fee_sum            tmoney,
    po_number          tstr200,
    invoice_issue_date tdatetime  not null,
    invoice_due_date   tdatetime  not null,
    invoice_note       tstr400
);

alter table st_invoices
    owner to dev_sss_store_admin;

create table st_transactions
(
    transaction_id       tidbigcode   not null
        constraint pk_transactions
            primary key,
    transaction_num      tstr50       not null
        constraint ak_st_transactions
            unique,
    invoice_id           tidbigcode   not null
        constraint fk_transactions_invoice_id
            references st_invoices,
    transaction_currency tcurrencyiso not null,
    transaction_sum      tmoney       not null,
    tansaction_date      tdatetime    not null,
    transaction_note     tstr400,
    payment_method_id    tidcode,
    bank_code            tstr50,
    bank_name            tstr100,
    account_num          tstr100
);

alter table st_transactions
    owner to dev_sss_store_admin;

create table st_invoices_hist
(
    invoice_id         tidbigcode not null,
    actual_date        tdatetime  not null,
    invoice_kind_id    tinteger,
    order_id           tidbigcode,
    invoice_number     tstr50,
    total_amount       tmoney,
    supplier_name      tstr200,
    supplier_address   tstr200,
    supplier_email     tstr100,
    reciever_name      tstr200,
    reciever_address   tstr400,
    reciever_email     tstr100,
    subtotal_amount    tmoney,
    vat_amount         tmoney,
    tax_sum            tmoney,
    discount_sum       tmoney,
    transactions_sum   tmoney,
    fee_sum            tmoney,
    po_number          tstr200,
    invoice_issue_date tdatetime,
    invoice_due_date   tdatetime,
    invoice_note       tstr400
);

alter table st_invoices_hist
    owner to dev_sss_store_admin;

create table st_units_of_measurement_ref
(
    unit_id   tinteger not null
        constraint pk_unit_ref
            primary key,
    unit_name tstr400  not null,
    unit_code tstr50   not null
        constraint ak_st_unit_ref
            unique
);

alter table st_units_of_measurement_ref
    owner to dev_sss_store_admin;

create table st_product_attrs
(
    product_attrs_id      tidbigcode not null
        constraint pk_product_attrs
            primary key
        constraint fk_st_product_attrs_attr_id
            references core_entities,
    product_attrs_name    tstr400    not null
        constraint st_product_attrs_name_ref
            unique,
    product_attrs_code    tstr50     not null
        constraint ak_st_product_attrs_ref
            unique,
    product_attrs_type    tstr10     not null,
    product_attrs_unit_id tinteger   not null
        constraint fk_product_attrs_unit_id
            references st_units_of_measurement_ref
);

alter table st_product_attrs
    owner to dev_sss_store_admin;

create table st_product_attrs_hist
(
    product_attrs_id      tidbigcode not null,
    actual_date           tdatetime  not null,
    product_attrs_name    tstr400,
    product_attrs_code    tstr50,
    product_attrs_type    tstr10,
    product_attrs_unit_id tinteger
);

alter table st_product_attrs_hist
    owner to dev_sss_store_admin;

create table st_product2attrs
(
    product2attrs_id         tidbigcode not null
        constraint pk_product2attrs
            primary key
        constraint fk_st_product2attrs_attr_id
            references core_entities,
    product2attrs_product_id tidbigcode not null
        constraint fk_product2attrs_product_id
            references st_products,
    product2attrs_attr_id    tidbigcode not null
        constraint fk_product2attrs_attrs_id
            references st_product_attrs
);

alter table st_product2attrs
    owner to dev_sss_store_admin;

create table st_manufactures
(
    manufactures_id  tidbigcode not null
        constraint pk_manufactures
            primary key
        constraint fk_st_manufactures_manufacture_id
            references core_entities,
    manufacture_name tstr200    not null,
    manufacture_code tstr50     not null
        constraint ak_st_manufactures_ref
            unique
);

alter table st_manufactures
    owner to dev_sss_store_admin;

create table st_manufactures_hist
(
    manufactures_id  tidbigcode not null,
    actual_date      tdatetime  not null,
    manufacture_name tstr200    not null,
    manufacture_code tstr50     not null
);

alter table st_manufactures_hist
    owner to dev_sss_store_admin;

create table st_manufacture2products
(
    manufacture2products_id             tidbigcode not null
        constraint pk_manufacture2products
            primary key
        constraint fk_st_manufacture2products_manufacture_id
            references core_entities,
    manufacture2products_product_id     tidbigcode not null
        constraint fk_manufacture2products_product_id
            references st_products,
    manufacture2products_manufacture_id tidbigcode not null
        constraint fk_manufacture2products_manufacture_id
            references st_manufactures,
    manufacture2products_code           tstr50     not null
        constraint ak_st_manufacture2products_ref
            unique
);

alter table st_manufacture2products
    owner to dev_sss_store_admin;

create table st_manufacture2products_hist
(
    manufacture2products_id             tidbigcode not null,
    actual_date                         tdatetime  not null,
    manufacture2products_product_id     tidbigcode not null,
    manufacture2products_manufacture_id tidbigcode not null,
    manufacture2products_code           tstr50     not null
);

alter table st_manufacture2products_hist
    owner to dev_sss_store_admin;

create table st_real_product2attrs
(
    real_product2attrs_id                     tidbigcode not null
        constraint pk_real_product2attrs
            primary key
        constraint fk_st_real_product2attrs_prod_attribute_id
            references core_entities,
    real_product2attrs_product2attribute_id   tidbigcode not null
        constraint fk_real_product2attrs_product2attribute_id
            references st_product2attrs,
    real_product2attrs_manufacture2product_id tidbigcode not null
        constraint fk_real_product2attrs_manufacture2product_id
            references st_manufacture2products,
    real_product2attrs_value                  tstr200    not null
);

alter table st_real_product2attrs
    owner to dev_sss_store_admin;

create table st_real_product2attrs_hist
(
    real_product2attrs_id                     tidbigcode not null,
    actual_date                               tdatetime  not null,
    real_product2attrs_product2attribute_id   tidbigcode not null,
    real_product2attrs_manufacture2product_id tidbigcode not null,
    real_product2attrs_value                  tstr200    not null
);

alter table st_real_product2attrs_hist
    owner to dev_sss_store_admin;

