
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `audit_record` 
       drop 
       foreign key `FKdcrrgv6rkfw2ruvdja56un4ji`;

    alter table `audit_record` 
       drop 
       foreign key `FKlbvbyimxf6pxvbhkdd4vfhlnd`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `auditor_request` 
       drop 
       foreign key `FKjonb5lt00rmb868h6gjdjh1to`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `commercial` 
       drop 
       foreign key FK_tk5yvuytfoa0dgtibahrxwwkd;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `descriptor_duty` 
       drop 
       foreign key `FK57eqqlhihwvd53ykpmsiqlx2p`;

    alter table `descriptor_duty` 
       drop 
       foreign key `FKqitedkrksd2w8qyp1fp5eao9f`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FKfqwyynnbcsq0htxho3vchpd2u`;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `message_thread_message` 
       drop 
       foreign key `FKka0a2jm3m6obl7wa6586cqyp4`;

    alter table `message_thread_message` 
       drop 
       foreign key `FKp1bkunf5gyu1vtt1q3f2djagy`;

    alter table `non_commercial` 
       drop 
       foreign key FK_1px28k1t0j3coqn549p1ru8op;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `user_in_thread` 
       drop 
       foreign key `FKm1jfnd156atd70id3r1x14lea`;

    alter table `user_in_thread` 
       drop 
       foreign key `FKiyt6dn1i7o94045st5aai7adx`;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit_record`;

    drop table if exists `auditor`;

    drop table if exists `auditor_request`;

    drop table if exists `authenticated`;

    drop table if exists `challenge`;

    drop table if exists `commercial`;

    drop table if exists `company_record`;

    drop table if exists `consumer`;

    drop table if exists `customisation_parameters`;

    drop table if exists `descriptor`;

    drop table if exists `descriptor_duty`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `fernandez_bulletin`;

    drop table if exists `investor_record`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `message_thread`;

    drop table if exists `message_thread_message`;

    drop table if exists `non_commercial`;

    drop table if exists `offer`;

    drop table if exists `pantoja_bulletin`;

    drop table if exists `provider`;

    drop table if exists `raposo_bulletin`;

    drop table if exists `request`;

    drop table if exists `sanchez_bulletin`;

    drop table if exists `sponsor`;

    drop table if exists `user_account`;

    drop table if exists `user_in_thread`;

    drop table if exists `worker`;

    drop table if exists `hibernate_sequence`;
