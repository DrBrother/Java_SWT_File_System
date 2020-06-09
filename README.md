# PPVIS-4 Разработка диалога для работы с файловой системой. (аналог JFileChooser)
Шадрин Е.Д. , гр. 821701 ЛР-4

Вариант 2 Разработка диалога для работы с файловой системой. (аналог JFileChooser)
В диалоге должны быть предусмотрены несколько функциональных областей:
1.	Просмотр содержимого текущего каталога. Должны быть поддержаны следующие режимы отображения каталога:
a)	в виде таблицы (в колонках отображаются имя файла, расширение, размер, дата последнего изменения в формате dd.mm.yyyy),
b)	в виде списка,
c)	в виде папок.
Режим отображения переключается при помощи меню или при помощи соответствующего элемента панели инструментов.
2.	Древовидный просмотр файловой системы. В дереве должны быть отображены только каталоги. При выборе некоторого каталога в дереве, содержимое каталога должно быть отображено в рабочей области 1.
3.	Поддержка домашнего каталога. При выборе данной функции меню или элемента на панели инструментов, осуществляется переход в домашний каталог.
4.	Отображение списка доступных дисков для ОС «Windows».
5.	Поддержка фильтров для файлов.
6.	Поле для ввода имени файла.
После реализации данного диалога он должен быть интегрирован в лабораторные работу 2, через jar файл, который содержит только реализацию filechooser.
