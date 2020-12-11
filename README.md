# dotCom
Игра морской бой
На поле (в этой версии) 10 на 10, размещаютья 5 трех палубных кораблей.

class DotCom хранит имя и расположение коробля и умеет проверять  попадания в себя.
class GameHelper  для вспомогательных фуекций и хранит поле для игры:
    - расстановка кораблей на поле public ArrayList<String> placeDotCom(int comSize)
    - проверяет можно ли рзместить корабль в данных координатах private boolean ifClean(int loc) 
    - пользовельский ввод для выстрела public String getUserInput(String prompt)
class DocComGame основной класс игры, запускает и проводит игру.
    
    
    