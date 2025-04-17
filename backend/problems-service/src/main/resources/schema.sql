CREATE SEQUENCE IF NOT EXISTS problem_no_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

ALTER TABLE problem
DROP CONSTRAINT IF EXISTS FK_problem_snippet;

ALTER TABLE problem
ADD CONSTRAINT FK_problem_snippet FOREIGN KEY (snippet_id)
REFERENCES snippet(id) ON DELETE CASCADE;

ALTER TABLE problem
DROP CONSTRAINT IF EXISTS FK_problem_file_content;

ALTER TABLE problem
ADD CONSTRAINT FK_problem_file_content FOREIGN KEY (file_content_id)
REFERENCES file_content(id) ON DELETE CASCADE;