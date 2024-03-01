SET search_path TO web;

INSERT INTO city(city_name)
VALUES
('Хабаровск'),
('Оренбург'),
('Кишинев'),
('Рязань'),
('Вышний Волочек'),
('Новосибирск'),
('Курск'),
('Санкт-Петербург'),
('Ставрополь'),
('Челябинск'),
('Воронеж'),
('Барнаул'),
('Нижний Новгород'),
('Харьков'),
('Омск'),
('Тверь'),
('Москва');

INSERT INTO auth(mail, pwd, auth_role)
VALUES
('first@mail.ru'::email, 'firstpwd', 'соискатель'::role_name),
('second@mail.ru'::email, 'secondpwd', 'студент'::role_name),
('third@mail.ru'::email, 'thirdpwd', 'работодатель'::role_name),
('fourth@mail.ru'::email, 'fourthpwd', 'работодатель'::role_name);

INSERT INTO edlevel(edlevel_name)
VALUES
('Бакалавриат'),
('Специалитет'),
('Магистратура'),
('Аспирантура'),
('Доктонатура');

INSERT INTO experience(company, "position", begin_date, end_date, salary)
VALUES
('ОАО Кадровое Агенство', 'Сеньор Python разработчик', DATE '2010-07-10', DATE '2023-07-21', 300000::BIGINT);
INSERT INTO experience(company, "position", begin_date, salary)
VALUES
('ПАО Сбербанк', 'Руководитель отдела поставок', DATE '2020-06-20', 250000::BIGINT);

INSERT INTO education(edlevel_id, institute, faculty, begin_date, end_date)
VALUES
(1, 'МГУ им М.В. Ломоносова', 'ВМК', DATE '2021-09-01', DATE '2025-06-20'),
(1, 'МГУ им М.В. Ломоносова', 'ФИИТ', DATE '2020-09-01', DATE '2024-06-20');

INSERT INTO applicant(auth_id, fullname, contact_info, age, city_id, education, work_experience)
VALUES
(1, 'Ревва Тимур Дмитриевич', '89391689376', 40, 2, '{1}'::BIGINT[], '{2}'::BIGINT[]),
(2, 'Варнава Иван Романович', '89058544610', 20, 10, '{2}'::BIGINT[], '{1}'::BIGINT[]);

INSERT INTO company(auth_id, company_name, workers_id, vacancies)
VALUES
(3, 'ПАО Сбербанк', '{2}'::BIGINT[], '{1}'::BIGINT[]),
(4, 'ООО Студенческая столовая', '{}'::BIGINT[], '{2}'::BIGINT[]);

INSERT INTO vacancy(company_id, vacancy_name, salary, requirements)
VALUES
(1, 'Директор по развитию', 100000, 'Продвинутое владение Word'),
(2, 'Руководитель отдела аналитики', 500000, 'Умение работать в команде');

INSERT INTO resume(applicant_id, desired_position, desired_salary, education, work_experience)
VALUES
(1, 'Директор по развитию', 1000000, '{1}'::BIGINT[], '{2}'::BIGINT[]),
(2, 'Руководитель отдела аналитики', 500000, '{2}'::BIGINT[], '{1}'::BIGINT[]);
