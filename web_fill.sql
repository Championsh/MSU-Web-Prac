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
('first@mail.ru'::email, 'firstpwd', 'соискатель'),
('second@mail.ru'::email, 'secondpwd', 'студент'),
('third@mail.ru'::email, 'thirdpwd', 'работодатель'),
('fourth@mail.ru'::email, 'fourthpwd', 'работодатель');

INSERT INTO edlevel(edlevel_name)
VALUES
('Бакалавриат'),
('Специалитет'),
('Магистратура'),
('Аспирантура'),
('Доктонатура');

INSERT INTO applicant(auth_id, fullname, contact_info, age, city_id)
VALUES
(1, 'Ревва Тимур Дмитриевич', '89391689376', 40, 2),
(2, 'Варнава Иван Романович', '89058544610', 20, 10);

INSERT INTO experience(applicant_id, company, "position", begin_date, end_date, salary)
VALUES
(1, 'ОАО Кадровое Агенство', 'Мидл Python разработчик', DATE '2010-07-10', DATE '2020-06-19', 300000::BIGINT),
(2, 'ОАО ИТ Компания', 'Джуниор Java разработчик', DATE '2020-07-10', DATE '2024-02-21', 150000::BIGINT),
(1, 'ПАО Сбербанк', 'Руководитель отдела поставок', DATE '2020-06-20', DATE '2024-02-21', 250000::BIGINT);

INSERT INTO education(applicant_id, edlevel_id, institute, faculty, begin_date, end_date)
VALUES
(1, 1, 'МГУ им М.В. Ломоносова', 'ВМК', DATE '2000-09-01', DATE '2004-06-20'),
(2, 1, 'МГУ им М.В. Ломоносова', 'ФИИТ', DATE '2020-09-01', DATE '2024-06-20');

INSERT INTO resume(applicant_id, desired_position, desired_salary)
VALUES
(1, 'Директор по развитию', 1000000),
(2, 'Руководитель отдела аналитики', 500000);

INSERT INTO experience_resume(resume_id, experience_id, additional_info)
VALUES
(1, 1, 'Продвинутое владение Word'),
(1, 3, ''),
(2, 2, 'Умение работать в команде');

INSERT INTO education_resume(resume_id, education_id)
VALUES
(1, 1),
(2, 2);

INSERT INTO company(auth_id, company_name)
VALUES
(3, 'ПАО Сбербанк'),
(4, 'ОАО ИТ Компания');

INSERT INTO vacancy(company_id, vacancy_name, salary, requirements)
VALUES
(1, 'Директор по развитию', 1000000, 'Продвинутое владение Word'),
(2, 'Руководитель отдела аналитики', 500000, 'Умение работать в команде');

INSERT INTO applicant_company(applicant_id, company_id, begin_date, end_date, position_name, salary)
VALUES
(1, 1, DATE '2020-06-20', DATE '2024-02-21', 'Руководитель отдела поставок', 250000::BIGINT),
(2, 2, DATE '2020-07-10', DATE '2024-02-21', 'Джуниор Java разработчик', 150000::BIGINT);

