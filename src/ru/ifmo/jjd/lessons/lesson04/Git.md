первое что нужно сделать в проекте для того чтобы подключить его к системе контроля версий - это инициализировать гит репозиторий

команда git init
выполнить её необходимо в корневой папке проекта

в результате выполнения появляется скрытая папка .git


если нужно, чтобы гит не учитывал (игнорировал) какие-то файлы и папки - это нужно явно указать.
для этого нужно чтобы:
- в корневой папке проекта был установлен файл .gitignore
- в него как в текстовый файл указать относительные пути до всех файлов и папок, которые гит должен игнорировать
	(если файлы-папки находятся в текущей папке (в корне) указание ./ не нужно)
	
	в проектах idea игнорируется обычно папки out/ и .idea/ и прочие локальные файлы, созданные средой
	
	
Перед тем как сохранить состояние проекта, необходимо определить какие файлы нужно сохранять.
Для этого необходимо явно указать, файлы/папки, чьё текущее состояние необходимо сохранить
команда git add %путьдофайла/имяфайла%

добавляется либо отдельный файл
либо папка со всем содержимым
несколько целей для git add указать нельзя, необходимо указывать по одному

	.gitignore необходимо добавлять всегда
	
Команда git status позволяет получить сводку. В ней указано, состояние каких файлов будет сохранено при следующем действии (git commit), а каких - нет
Файлы, указанные в .gitignore в эту сводку не попадает.


Следующее действие - фактическое сохранение файлов, добавленных через git add
команда git commit -m "комментарий к коммиту"
результатом будет сообщение о выполнении сохранения
и в git будет сохранено первое состояние проекта

Процедура сохранения (после любых изменений) всегда будет состоять из двух этапов:
 - выбрать что именно сохранить (git add) --- индексакция изменений
 - фактически сохранить (git commit) --- фиксация изменений

Что будет, если состав / состояние файлов поменяется между git add и git commit? -- сохранено будет только то, что было проиндексировано git add. 
Чтобы избежать потери последних изменений нужно снова сделать git add

Как отменить добавление файла через git add -- git rm %путьдофайла/имяфайла%

Добавление новых файлов, переименование, перемещение файлов, удаление файлов, изменение содержимого файлов -- если это необходимо сохранить,
нужно явно указать эти файлы через git add
и затем сделать git commit (крайне желательно с комментарием)



--------------------------------------------------------------------------------------




Связывание с удалённым репозиторием:
зайти на github, например, и создать там новый репозиторий.

связь между локальным и удалённым репозиторием создаётся один раз
команда 
git remote add origin https://github.com/SlepuhinAlexander/tst-git.git
выполняется в рабочей папке проекта (локально)

эта команда указывает локальному репозиторию направление на привязанный к нему удалённого репозитория
результатом будет фактическое связывание локального репозитория с удалённым.

здесь origin - это имя основного удалённого репозитория.
(их может быть и несколько. Для того чтобы привязать другой удалённый репозиторий - та же команда, только нужно указать другое имя).


далее, чтобы синхронизовать текущее состояние локального репозитория с удалённым выполняется команда git push
git push -u origin master

git push отправляет все ранее незапушенные коммиты в указанный удалённый репозиторий
origin - имя удалённого репозитория (здесь -- основной)
master - имя ветки (здесь -- основной) в целевом репозитории

ветка master создаётся по умолчанию.

если имя репозитория / ветки не меняем, то в дальнейшем можно использовать просто git push (он использует последние использованные репозиторий и ветку)












--------------------------------------------------------------------------------------

для того чтобы скопировать проект с удалённого репозитория на локальный (на локальной машине проекта ещё нет) необходима команда
git clone %ссылка_на_репозиторий%
выполнять необходимо в директории в которую нужно склонировать репозиторий

в результате будет создан локальный гит репозиторий, наполненный скопированными данными


-----------------------------------------------------------------------------------

для получения изменений из удалённого репозитория в существующий локальный репозиторий используется команда
git pull

в результате состояние локального репозитория будет синхронизовано соответствии с удалённым репозиторием


--------------------------------------------------------------------------------------------

конфликты и их разрешения.

когда возникают конфликты:
в момент pull
в момент слияния веток
в момент, когда локальные изменения ещё не сохранены (не закоммичены)

Например, с момента, когда было получено последнее состояние на локальный репозиторий, состояние на удалённом репозитории изменено, и на локальном в тех же строках изменено иначе.
при попытке git pull в локальный репозиторий у git возникнет конфликт: не понятно какое из двух изменённых состояний необходимо применить.
т.е. git pull не произойдёт. никакие изменения не подтянутся

далее, если локальные изменения всё же нужно сохранить:
 - git add
 - git commit
(локальные изменения локально сохранены)
 - при попытке git push будет конфликт: слияние невозможно
в результате в файле будут записаны оба варианта изменений и нужно будет вручную разрешить конфликт

имеет такой вид
    <<<<<<< HEAD
    New message
    =======
    Some conflicted text
    >>>>>>> 38508657ad15813900300a8896a51cfa1631be16
здесь HEAD указание на текущий коммит

необходимо установить конечное содержимое файла с учётом того что вы хотите сохранить
затем git add, git commit
и git pull чтобы убедиться что конфликтов больше нет


вариант, когда локальные изменения ещё не сохранены (не закоммичены) и мы хотим их "спрятать"
команда git stash
она возвращает проект к состоянию последнего коммита

(stash-ей может быть сколько угодно)

варианты как поступить со stash:
 - спрятать и не открывать. git pull при этом выполнится без каких-либо проблем
 - вернуть
 
команда на возврашение отложенного в stash:
git stash apply
при этом может быть конфликт: если файл уже был изменён с момента откладывания в stash

===========================
------

команда на сброс
git reset --hard HEAD
отменяет все изменения, возвращает в состояние переданного коммита
здесь HEAD - указатель на последний коммит
вместо него может быть hash-сумма конкретного интересующего коммита


-----

git revert <коммит>
создаёт новый коммит поверх предыдущего, в котором отменяет все изменения, которые были сохранены в <commit>

------

git log
посмотреть историю сделанных коммитов
от последнего сделанного коммита (в начале(вверху)) до самого первого

в IDEA история коммитов содержится во вкладке Version Control (вкладка log)
там же в контекстном меню коммита есть команды на перемещения по коммитам


команды перемещения по коммитам:
1. команда для просмотра коммита:
git checkout <коммит>
git checkout <имя ветки> -- вернуться назад
не применим, если есть незакоммиченные изменения. их можно положить в stash

2. возвращение к какому-то коммиту и продолжение работы из этого состояния.
фактически при этом будет создана новая ветка. в которую будут будут дальнейшие коммиты писаться.
git checkout -b <имя_новой_ветки> <коммит>
команда создаёт новую ветку с указанным именем; работа переключается на эту ветку. и ветка начинается с указанного коммита

git checkout <имя ветки> -- просто переключение на эту ветку.

----------------------------------------------------------

Ветки: 

git branch
показывает список всех имеющихся веток
текущая активная подсвечена залёным

git branch <имя_ветки>
создание новой ветки

git checkout <имя_ветки>
переключение на указанную ветку.

git push <имя_репозитория> <имя_ветки>
отправить на удалённый репозиторий указанную ветку со всеми изменениями в ней

git push <имя_репозитория> --delete <имя_ветки>
удалить ветку из удалённого репозитория

git branch -d <имя ветки>
удаление ветки из локального репозитория

слияние веток:
процедура объединения двух параллельных веток для того чтобы объединить 
git merge <имя_другой_ветки>
производит слияние текущей ветки с другой_веткой
результат будет в текущей ветке