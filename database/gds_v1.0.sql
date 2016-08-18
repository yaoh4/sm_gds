-- Lookup values for IC Submission screen

INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (18, 'YES', 'Yes', 'Yes', 'IC_APPROVED_BY_GPA', sysdate, user)
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (19, 'NO', 'No', 'No', 'IC_APPROVED_BY_GPA', sysdate, user)
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (20, 'PARTIALLY', 'Partially', 'Partially', 'IC_APPROVED_BY_GPA', sysdate, user)
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (21, 'NA', 'Not Applicable', 'Not Applicable', 'IC_APPROVED_BY_GPA', sysdate, user)

INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (22,'PROV', 'Provisional', 'Provisional', 'IC_PROV_OR_FINAL', sysdate, user);
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (23, 'FINAL', 'Final', 'Final', 'IC_PROV_OR_FINAL', sysdate, user)

INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (24, 'YES', 'Yes', 'Yes', 'IC_FOR_FUTURE_USE', sysdate, user)
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (25, 'NO', 'No', 'No', 'IC_FOR_FUTURE_USE', sysdate, user)

INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (35, 'YES', 'Yes', 'Yes', 'IC_DUL_VERIFIED', sysdate, user);
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (36, 'NO', 'No', 'No', 'IC_DUL_VERIFIED', sysdate, user);
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (37, 'PARTIALLY', 'Partially', 'Partially', 'IC_DUL_VERIFIED', sysdate, user);
INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (38, 'NA', 'Not Applicable', 'Not Applicable', 'IC_DUL_VERIFIED', sysdate, user);

INSERT INTO LOOKUP_T (ID, CODE, DISPLAY_NAME, DESCRIPTION, DISCRIMINATOR, CREATED_DATE, CREATED_BY)
VALUES (51, 'IC', 'Institutional Certification', 'Institutional Certification', 'Page', sysdate, user);