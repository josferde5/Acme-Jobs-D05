
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `more_info` varchar(255),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `justification` varchar(255),
        `qualifications` varchar(255),
        `reference_number` varchar(255),
        `skills` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `audit_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `final_mode` bit not null,
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor_request` (
       `id` integer not null,
        `version` integer not null,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        `authenticated_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `goal_bronze` varchar(255),
        `goal_gold` varchar(255),
        `goal_silver` varchar(255),
        `reward_bronze_amount` double precision,
        `reward_bronze_currency` varchar(255),
        `reward_gold_amount` double precision,
        `reward_gold_currency` varchar(255),
        `reward_silver_amount` double precision,
        `reward_silver_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `commercial` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `target` varchar(255),
        `sponsor_id` integer,
        `credit_card` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `company_record` (
       `id` integer not null,
        `version` integer not null,
        `ceo` varchar(255),
        `company` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `incorporated` bit,
        `sector` varchar(255),
        `stars` integer,
        `telephone` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation_parameters` (
       `id` integer not null,
        `version` integer not null,
        `spam_words_en` varchar(255),
        `spam_words_sp` varchar(255),
        `threshold` double precision,
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor_duty` (
       `descriptor_id` integer not null,
        `duties_id` integer not null
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `percent_time` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `fernandez_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `company` varchar(255),
        `contact` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `moment` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor_record` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `sector` varchar(255),
        `stars` integer,
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `final_mode` bit not null,
        `more_info` varchar(255),
        `reference` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `title` varchar(255),
        `descriptor_id` integer,
        `employer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread_message` (
       `message_thread_id` integer not null,
        `messages_id` integer not null
    ) engine=InnoDB;

    create table `non_commercial` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `target` varchar(255),
        `sponsor_id` integer,
        `jingle` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `pantoja_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `contact` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `raposo_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `place` varchar(255),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sanchez_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sponsor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `credit_card` varchar(255),
        `organisation_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_in_thread` (
       `id` integer not null,
        `version` integer not null,
        `authenticated_id` integer,
        `message_thread_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications_record` varchar(255),
        `skills_record` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `application` 
       add constraint UK_rf84q38qr35ymh5nn0dcxfdue unique (`reference_number`);

    alter table `descriptor_duty` 
       add constraint UK_kvr5rclgwa51d625rmx13ke96 unique (`duties_id`);

    alter table `job` 
       add constraint UK_7jmfdvs0b0jx7i33qxgv22h7b unique (`reference`);

    alter table `message_thread_message` 
       add constraint UK_bx8ll7j8be93gcj4mnbmvm2rk unique (`messages_id`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);

    alter table `request` 
       add constraint UK_9mxq3powq8tqctclj0fbi2nih unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `audit_record` 
       add constraint `FKdcrrgv6rkfw2ruvdja56un4ji` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `audit_record` 
       add constraint `FKlbvbyimxf6pxvbhkdd4vfhlnd` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `auditor_request` 
       add constraint `FKjonb5lt00rmb868h6gjdjh1to` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `commercial` 
       add constraint FK_tk5yvuytfoa0dgtibahrxwwkd 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `descriptor_duty` 
       add constraint `FK57eqqlhihwvd53ykpmsiqlx2p` 
       foreign key (`duties_id`) 
       references `duty` (`id`);

    alter table `descriptor_duty` 
       add constraint `FKqitedkrksd2w8qyp1fp5eao9f` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FKfqwyynnbcsq0htxho3vchpd2u` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `message_thread_message` 
       add constraint `FKka0a2jm3m6obl7wa6586cqyp4` 
       foreign key (`messages_id`) 
       references `message` (`id`);

    alter table `message_thread_message` 
       add constraint `FKp1bkunf5gyu1vtt1q3f2djagy` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `non_commercial` 
       add constraint FK_1px28k1t0j3coqn549p1ru8op 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `sponsor` 
       add constraint FK_20xk0ev32hlg96kqynl6laie2 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `user_in_thread` 
       add constraint `FKm1jfnd156atd70id3r1x14lea` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `user_in_thread` 
       add constraint `FKiyt6dn1i7o94045st5aai7adx` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
