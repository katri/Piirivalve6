CREATE TABLE RIIGI_ADMIN_YKSUS (
       riigi_admin_yksus_ID INTEGER,
       kood                 VARCHAR(20),
       nimetus              VARCHAR(100),     
       PRIMARY KEY (riigi_admin_yksus_ID)      
);

CREATE UNIQUE INDEX XPKRIIGI_ADMIN_YKSUS ON RIIGI_ADMIN_YKSUS
(
       riigi_admin_yksus_ID
);

CREATE TABLE VAEOSA (
       vaeosa_ID_id         INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       kood                 VARCHAR(20) NOT NULL,
       nimetus              VARCHAR(100) NOT NULL,
       kommentaar           LONGVARCHAR,
       riigi_admin_yksus_ID INTEGER NOT NULL,
       alates               DATE NOT NULL,
       kuni                 DATE NOT NULL,
       PRIMARY KEY (vaeosa_ID_id), 
       FOREIGN KEY (riigi_admin_yksus_ID)
                             REFERENCES RIIGI_ADMIN_YKSUS
                             ON DELETE RESTRICT
);

CREATE UNIQUE INDEX XPKVAEOSA ON VAEOSA
(
       vaeosa_ID_id
);

CREATE INDEX XIF1VAEOSA ON VAEOSA
(
       riigi_admin_yksus_ID
);


CREATE TABLE PIIRIPUNKT (
       piiripunkt_ID        INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       kood                 VARCHAR(20) NOT NULL,
       nimetus              VARCHAR(100) NOT NULL,
       GPS_Longituide       DECIMAL(9) NOT NULL,
       GPS_latitude         DECIMAL(9) NOT NULL,
       korgus_merepinnast   DECIMAL(6) NOT NULL,
       kommentaar           LONGVARCHAR,
       alates               DATE NOT NULL,
       kuni                 DATE NOT NULL,
       PRIMARY KEY (piiripunkt_ID)
);

CREATE UNIQUE INDEX XPKPIIRIPUNKT ON PIIRIPUNKT
(
       piiripunkt_ID
);


CREATE TABLE VAHTKOND (
       vahtkond_ID          INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       kood                 VARCHAR(20),
       nimetus              VARCHAR(60),
       kommentaar           LONGVARCHAR,
       alates               DATE,
       kuni                 DATE,
       piiripunkt_ID        INTEGER,
       vaeosa_ID_id         INTEGER,
       PRIMARY KEY (vahtkond_ID), 
       FOREIGN KEY (vaeosa_ID_id)
                             REFERENCES VAEOSA
                             ON DELETE SET NULL, 
       FOREIGN KEY (piiripunkt_ID)
                             REFERENCES PIIRIPUNKT
                             ON DELETE SET NULL
);

CREATE UNIQUE INDEX XPKVAHTKOND ON VAHTKOND
(
       vahtkond_ID
);

CREATE INDEX XIF41VAHTKOND ON VAHTKOND
(
       piiripunkt_ID
);

CREATE INDEX XIF42VAHTKOND ON VAHTKOND
(
       vaeosa_ID_id
);

CREATE TABLE PIIRILOIK (
       piiriloik_ID         INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       kood                 CHAR(18),
       nimetus              VARCHAR(60),
       GPS_koordinaadid     LONGVARCHAR,
       kommentaar           LONGVARCHAR,
       PRIMARY KEY (piiriloik_ID)
);

CREATE UNIQUE INDEX XPKPIIRILOIK ON PIIRILOIK
(
       piiriloik_ID
);

CREATE TABLE VAHTKONND_PIIRILOIGUL (
       vahtkond_piiriloiul_ID INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       piiriloik_ID         INTEGER NOT NULL,
       vahtkond_ID          INTEGER NOT NULL,
       alates               DATE,
       kuni                 DATE,
       kommentaar           LONGVARCHAR,
       PRIMARY KEY (vahtkond_piiriloiul_ID), 
       FOREIGN KEY (vahtkond_ID)
                             REFERENCES VAHTKOND
                             ON DELETE RESTRICT, 
       FOREIGN KEY (piiriloik_ID)
                             REFERENCES PIIRILOIK
                             ON DELETE RESTRICT
);

CREATE UNIQUE INDEX XPKVAHTKONND_PIIRILOIGUL ON VAHTKONND_PIIRILOIGUL
(
       vahtkond_piiriloiul_ID
);

CREATE INDEX XIF39VAHTKONND_PIIRILOIGUL ON VAHTKONND_PIIRILOIGUL
(
       piiriloik_ID
);

CREATE INDEX XIF40VAHTKONND_PIIRILOIGUL ON VAHTKONND_PIIRILOIGUL
(
       vahtkond_ID
);

CREATE UNIQUE INDEX XPKPIIRIPUNKTI_ORG_YKSUS ON PIIRIPUNKTI_ORG_YKSUS
(
       piiripunkti_org_yksus_ID
);

CREATE INDEX XIF25PIIRIPUNKTI_ORG_YKSUS ON PIIRIPUNKTI_ORG_YKSUS
(
       piiripunkt_ID
);

CREATE INDEX XIF26PIIRIPUNKTI_ORG_YKSUS ON PIIRIPUNKTI_ORG_YKSUS
(
       ylem_org_yksus_ID
);


CREATE TABLE PIIRILOIGU_HALDAJA (
       piiriloigu_haldaja_ID INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       piiriloik_ID         INTEGER,
       piiripunkt_ID        INTEGER NOT NULL,
       vaeosa_ID_id         INTEGER NOT NULL,
       alates               DATE,
       kuni                 DATE,
       kommentaar           LONGVARCHAR,
       PRIMARY KEY (piiriloigu_haldaja_ID), 
       FOREIGN KEY (vaeosa_ID_id)

                             REFERENCES VAEOSA
                             ON DELETE RESTRICT, 
       FOREIGN KEY (piiripunkt_ID)
                             REFERENCES PIIRIPUNKT
                             ON DELETE SET NULL, 
       FOREIGN KEY (piiriloik_ID)
                             REFERENCES PIIRILOIK
                             ON DELETE SET NULL
);

CREATE UNIQUE INDEX XPKPIIRILOIGU_HALDAJA ON PIIRILOIGU_HALDAJA
(
       piiriloigu_haldaja_ID
);

CREATE INDEX XIF36PIIRILOIGU_HALDAJA ON PIIRILOIGU_HALDAJA
(
       piiriloik_ID
);

CREATE INDEX XIF37PIIRILOIGU_HALDAJA ON PIIRILOIGU_HALDAJA
(
       piiripunkt_ID
);

CREATE INDEX XIF38PIIRILOIGU_HALDAJA ON PIIRILOIGU_HALDAJA
(
       vaeosa_ID_id
);


CREATE TABLE PIIRIPUNKTI_ALLUVUS (
       piiripunkti_alluvus_ID INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       alates               CHAR(18),
       kuni                 CHAR(18),
       kommentaar           CHAR(18),
       vaeosa_ID            INTEGER NOT NULL,
       piiripunkt_ID        INTEGER NOT NULL,
       PRIMARY KEY (piiripunkti_alluvus_ID), 
       FOREIGN KEY (piiripunkt_ID)
                             REFERENCES PIIRIPUNKT
                             ON DELETE RESTRICT, 
       FOREIGN KEY (vaeosa_ID)
                             REFERENCES VAEOSA
                             ON DELETE RESTRICT
);

CREATE UNIQUE INDEX XPKPIIRIPUNKTI_ALLUVUS ON PIIRIPUNKTI_ALLUVUS
(
       piiripunkti_alluvus_ID
);

CREATE INDEX XIF10PIIRIPUNKTI_ALLUVUS ON PIIRIPUNKTI_ALLUVUS
(
       vaeosa_ID
);

CREATE INDEX XIF27PIIRIPUNKTI_ALLUVUS ON PIIRIPUNKTI_ALLUVUS
(
       piiripunkt_ID
);


CREATE TABLE VAEOSA_ALLUVUS (
       vaeosa_alluvus_id    INTEGER,
       avaja                VARCHAR(32) NOT NULL,
       avatud               DATE NOT NULL,
       muutja               VARCHAR(32) NOT NULL,
       muudetud             DATE NOT NULL,
       sulgeja              VARCHAR(32),
       suletud              DATE NOT NULL,
       ylemus_vaeosa_ID     INTEGER NOT NULL,
       alluva_vaeosa_ID     INTEGER NOT NULL,
       alates               DATE NOT NULL,
       kuni                 DATE NOT NULL,
       kommentaar           LONGVARCHAR,
       PRIMARY KEY (vaeosa_alluvus_id), 
       FOREIGN KEY (alluva_vaeosa_ID)
                             REFERENCES VAEOSA
                             ON DELETE RESTRICT, 
       FOREIGN KEY (ylemus_vaeosa_ID)
                             REFERENCES VAEOSA
                             ON DELETE RESTRICT
);

CREATE UNIQUE INDEX XPKVAEOSA_ALLUVUS ON VAEOSA_ALLUVUS
(
       vaeosa_alluvus_id
);

CREATE INDEX XIF8VAEOSA_ALLUVUS ON VAEOSA_ALLUVUS
(
       ylemus_vaeosa_ID
);

CREATE INDEX XIF9VAEOSA_ALLUVUS ON VAEOSA_ALLUVUS
(
       alluva_vaeosa_ID
);


