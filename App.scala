import scala.io.StdIn.{readFloat, readInt, readLine}
import scala.annotation.tailrec

// 2.2 Домашняя работа по Scala
object App {
  def main(args: Array[String]): Unit = {
    // Печатаем "Hello, Scala"
    println("Hello, Scala!")
    println

    println("Задание a.")
    // a.i    Напишите программу, которая выводит фразу «Hello, Scala!» справа налево
    val msg = "Hello, Scala!"
    println(msg.reverse)
    // a.ii   Напишите программу, которая переводит всю фразу в нижний регистр
    println(msg.toLowerCase)
    // a.iii  Напишите программу, которая удаляет символ "!"
    println(msg.replace("!", ""))
    // a.iv   Напишите программу, которая добавляет в конец фразы «and goodbye Python!»
    println(msg + " and goodbye Python!")

    /* b. Напишите программу, которая вычисляет ежемесячный оклад сотрудника после вычета налогов.
          На вход вашей программе подаётся значение годового дохода до вычета налогов,
          размер премии – в процентах от годового дохода и компенсация питания. */
    println
    println("Задание b: вычислить ежемесячный оклад.")
    print("Введите годовой доход: ")
    val yearSalary = readInt()
    print("Введите размер премии: ")
    val bonus = readFloat()
    print("Введите компенсацию питания: ")
    val eatBonus = readInt()

    var monthSalary = ((yearSalary * (1 + bonus/100)) * 0.87 / 12) + eatBonus
    println("Ежемесячный оклад: " + monthSalary)

    /* с. Напишите программу, которая рассчитывает для каждого сотрудника отклонение (в процентах)
          от среднего значения оклада на уровень всего отдела. В итоговом значении должно учитываться
          в большую или меньшую сторону отклоняется размер оклада.
          На вход вашей программе подаются все значения, аналогичные предыдущей программе,
          а также список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75.
     */
    println
    println("Задание c: вычислить отклонение от среднего оклада.")
    var departmentSalaries = List(100, 150, 200, 80, 120, 75)
    println("Зарплаты в отделе:\t\t\t\t\t" + departmentSalaries.mkString("\t\t"))
    val departmentMean = departmentSalaries.sum / departmentSalaries.size
    print("Отклонение от среднего оклада, %:\t")
    for (n <- departmentSalaries) {
      val delta = (n - departmentMean) * 100.0 / departmentMean
      print(f"$delta%.1f\t")
    }
    println

    /* d. Попробуйте рассчитать новую зарплату сотрудника, добавив (или отняв, если сотрудник плохо себя вел)
          необходимую сумму с учётом результатов прошлого задания. Добавьте его зарплату в список и вычислите
          значение самой высокой зарплаты и самой низкой.
     */
    println
    println("Задание d: скорректировать зарплату сотрудника, определить max и min.")
    val delta = (monthSalary - departmentMean).abs
    print("Сотрудник работал хорошо (да/нет)? ")
    val replyString = readLine().toLowerCase

    if (replyString(0) == 'д') {
      println("Повышаем зарплату на " + delta)
      monthSalary = monthSalary + delta
    } else {
      println("Понижаем зарплату на " + delta / 2)
      monthSalary = monthSalary - delta / 2
    }

    departmentSalaries = departmentSalaries ::: List(monthSalary.toInt)
    println("Новые зарплаты в отделе:\t\t\t\t\t" + departmentSalaries.mkString("\t\t"))

    println("Максимальная зарплата в отделе: " + departmentSalaries.max)
    println("Минимальная зарплата в отделе: " + departmentSalaries.min)
    /* e. Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей.
          Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.
     */
    println
    println("Задание e: добавить двух сотрудников и отсортировать список.")

    departmentSalaries = departmentSalaries ::: List(350, 90)
    println("В отдел приняли двух новых сотрудников:\t\t\t" + departmentSalaries.mkString("\t\t"))
    departmentSalaries = departmentSalaries.sorted
    println("Список окладов по возрастанию:\t\t\t\t\t" + departmentSalaries.mkString("\t\t"))

    /* f. Кажется, вы взяли в вашу команду ещё одного сотрудника и предложили ему оклад 130 тысяч.
          Вычислите самостоятельно номер сотрудника в списке так, чтобы сортировка не нарушилась и добавьте его на это место.
     */
    println
    println("Задание f: добавить сотрудника и вставить в список.")

    val newWorker = 130
    println("В отдел добавили нового сотрудника с зарплатой " + newWorker)

    var i = -1
    for (n <- departmentSalaries)
      if (newWorker >= n) i = departmentSalaries.indexOf(n) + 1

    println("Добавим его в список окладов после элемента №" + (i-1))
    val front = departmentSalaries.take(i)
    val back = departmentSalaries.drop(i)
    /*
    Можно было ещё сделать так, но в этом случае нет самостоятельного поиска индекса
      val front = departmentSalaries.filter(_ < newWorker)
      val back = departmentSalaries.filter(_ > newWorker)
     */
    departmentSalaries = front ::: List(newWorker) ::: back
    println("Список окладов после добавления:\t\t\t\t" + departmentSalaries.mkString("\t\t"))

    /* g. Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
          На вход программе подаётся «вилка» зарплаты специалистов уровня middle.
     */
    println
    println("Задание g: вывести номера специалистов уровня middle.")

    print("Введите минимальную зарплату для middle: ")
    val middleSalaryMin = readInt()
    print("Введите максимальную зарплату для middle: ")
    val middleSalaryMax = readInt()

    for (n <- departmentSalaries) {
      if ((n >= middleSalaryMin) && (n <= middleSalaryMax)) println("Сотрудник №" + departmentSalaries.indexOf(n) + " (оклад " + n + ") - middle")
    }

    /* h. Однако наступил кризис и ваши сотрудники требуют повысить зарплату.
          Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7%
     */
    println
    println("Задание h: проиндексировать зарплату сотрудников на 7%.")
    println("Исходный список окладов:\t\t\t\t" + departmentSalaries.mkString("\t\t"))
    departmentSalaries = departmentSalaries.map(n => (n * 1.07).toInt)
    println("Оклады после индексации:\t\t\t\t" + departmentSalaries.mkString("\t\t"))

    /* i.*
    Ваши сотрудники остались недовольны и просят индексацию на уровень рынка. Попробуйте повторить ту же операцию,
    как и в предыдущем задании, но теперь вам нужно проиндексировать зарплаты на процент отклонения от среднего
    по рынку с учётом уровня специалиста. На вход вашей программе подаётся
    3 значения – среднее значение зарплаты на рынке для каждого уровня специалистов (junior, middle и senior)
    */
    println
    println("Задание i: проиндексировать зарплату сотрудников по категориям на уровень рынка.")
    // Проверено на 80, 120, 200
    print("Введите среднюю зарплату для junior: ")
    val juniorSalaryMarketMean = readInt()
    print("Введите среднюю зарплату для middle: ")
    val middleSalaryMarketMean = readInt()
    print("Введите среднюю зарплату для senior: ")
    val seniorSalaryMarketMean = readInt()

    var departmentSalariesIndexed = List[Double]()
    var juniorSalaries = List[Int]()
    var middleSalaries = List[Int]()
    var seniorSalaries = List[Int]()
    for (i <- departmentSalaries) {
      if (i <= 80) {
        //println(i + " - junior")
        juniorSalaries = i +: juniorSalaries
      } else if ((i > 80) && (i <= 120)) {
        //println(i + " - middle")
        middleSalaries = i +: middleSalaries
      } else {
        //println(i + " - senior")
        seniorSalaries = i +: seniorSalaries
      }
    }
    println("Junior list: " + juniorSalaries)
    println("Middle list: " + middleSalaries)
    println("Senior list: " + seniorSalaries)
    val juniorSalaryMean = juniorSalaries.sum / juniorSalaries.size
    val middleSalaryMean = middleSalaries.sum / middleSalaries.size
    val seniorSalaryMean = seniorSalaries.sum / seniorSalaries.size
    println("Junior Salary Mean: " + juniorSalaryMean)
    println("Middle Salary Mean: " + middleSalaryMean)
    println("Senior Salary Mean: " + seniorSalaryMean)

    val juniorSalaryDiff: Double = 1 - juniorSalaryMean.toDouble / juniorSalaryMarketMean.toDouble
    val middleSalaryDiff: Double = 1 - middleSalaryMean.toDouble / middleSalaryMarketMean.toDouble
    val seniorSalaryDiff: Double = 1 - seniorSalaryMean.toDouble / seniorSalaryMarketMean.toDouble
    println("Junior Salary Mean Diff: " + juniorSalaryDiff)
    println("Middle Salary Mean Diff: " + middleSalaryDiff)
    println("Senior Salary Mean Diff: " + seniorSalaryDiff)

    for (i <- departmentSalaries) {
      if (i <= 80) {
        //println(i + " - junior")
        departmentSalariesIndexed = (i + i * juniorSalaryDiff) +: departmentSalariesIndexed
      } else if ((i > 80) && (i <= 120)) {
        //println(i + " - middle")
        departmentSalariesIndexed = (i + i * middleSalaryDiff) +: departmentSalariesIndexed
      } else {
        //println(i + " - senior")
        departmentSalariesIndexed = (i + i * seniorSalaryDiff) +: departmentSalariesIndexed
      }
    }
    departmentSalariesIndexed = departmentSalariesIndexed.reverse
    println("Зарплаты в отделе до индексации:\t\t\t\t\t" + departmentSalaries.mkString("\t\t"))
    println("Зарплаты в отделе после индексации:\t\t\t\t\t" + departmentSalariesIndexed.mkString("\t\t"))

    /* j.**** (для тех, кто любит хардкор)
    Попробуйте самостоятельно вычислить средние значения уровня зарплат
    для data engineer’ов каждого уровня с помощью, например, https: //dev.hh.ru/.
    */

    /* k.*
    Попробуйте деанонимизировать ваших сотрудников – составьте структуру, которая позволит иметь знания о том,
    сколько зарабатывает каждый сотрудник (Фамилия и имя).
    */
    println
    println("Задание k: создать структуру с ФИО сотрудников и их зарплатами.")
    val workerSalaryMap = Map(
      "Ivanov Ivan" -> 100,
      "Petrov Petr" -> 150,
      "Sidorov Alexey" -> 200,
      "Egorov Nikolay" -> 80,
      "Fedorov Oleg" -> 120,
      "Smirnov Vasiliy" -> 75,
    )
    for ((i, j) <- workerSalaryMap) {
      println("Сотрудник: \t" + i + "\tзарплата: " + j)
    }

    /* l.*
    Выведите фамилию и имя сотрудников с самой высокой и самой низкой зарплатой (только не рассказывайте им об этом факте).
    */
    println
    println("Задание l: вывести ФИО сотрудников с max и min зарплатами.")
    var maxSalary = 0
    var workerWithMaxSalary = ""
    var minSalary = 1000
    var workerWithMinSalary = ""

    for ((i, j) <- workerSalaryMap) {
      //println(i, j)
      if (j > maxSalary) {
        maxSalary = j
        workerWithMaxSalary = i
      }
      if (j < minSalary) {
        minSalary = j
        workerWithMinSalary = i
      }
    }
    println("Минимальная зарплата = " + minSalary + " у сотрудника " + workerWithMinSalary)
    println("Максимальная зарплата = " + maxSalary + " у сотрудника " + workerWithMaxSalary)

    /* m.*
    Попробуйте запутать тех, кто может случайно наткнуться на эти данные – удалите для каждого сотрудника имя,
    переведите строку в нижний регистр, удалите гласные и разверните оставшиеся символы справа налево (abc -> cb).
    */
    println
    println("Задание m: анонимизировать ФИО сотрудников.")

    var workerSalaryMapAnonymized = Map.empty[String, Int]
    for ((i, j) <- workerSalaryMap) {
      workerSalaryMapAnonymized += (i.split(" ")(0).toLowerCase.replaceAll("a|e|i|o|u", "").reverse -> j)
    }
    println("Исходный список: " + workerSalaryMap)
    println("Анонимизированный список: " + workerSalaryMapAnonymized)

    /* n.*
    Попробуйте завернуть программу из пункта 3.b в функцию и входные значения переделать в параметры функции.
    */

    def calculateMonthSalary(yearSalary: Int, bonus: Int, eatBonus: Int): Int = {
      val monthSalary = ((yearSalary * (1 + bonus/100.0)) * 0.87 / 12) + eatBonus
      monthSalary.toInt
    }
    println("Ежемесячный оклад: " + calculateMonthSalary(1200, 5, 50))

    /* o.*
    Попробуйте написать функцию, которая вычисляет значение степени двойки:
    i.С помощью обычной рекурсии
    ii.** С помощью хвостовой рекурсии
    */
    println
    println("Задание o.i: вычислить степени двойки с использованием рекурсии.")

    def power2(n: Int): Int = {
      if (n <= 1) 2
      else {
        val pw = 2 * power2(n - 1)
        pw
      }
    }
    println(power2(10))

    println
    println("Задание o.ii: вычислить степени двойки с использованием хвостовой рекурсии.")
    def power2Tail(n: Int): Int = {
      @tailrec
      def loop(x: Int, acc: Int): Int = {
        if (x <= 0) acc
        else loop(x - 1, 2 * acc)
      }
      loop(n, 1)
    }

    println(power2Tail(10))


  }
}

