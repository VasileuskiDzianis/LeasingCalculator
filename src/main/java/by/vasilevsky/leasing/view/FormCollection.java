package by.vasilevsky.leasing.view;

import java.util.ArrayList;

import by.vasilevsky.leasing.domain.Company;
import by.vasilevsky.leasing.domain.Person;
import by.vasilevsky.leasing.service.CalcRequest;
import by.vasilevsky.leasing.service.UserValidator;

public class FormCollection {
    //links
    private static final String CALC = "vlsmain?link=calculator";
    private static final String CABINET_PRVT = "vlsmain?link=cabinetprvt";
    private static final String CABINET_BSN = "vlsmain?link=cabinetbsn";
    private static final String IDENT_PRVT = "vlsmain?link=identprvt";
    private static final String IDENT_BSN = "vlsmain?link=identbsn";
    private static final String ORDER_PRVT = "vlsmain?link=orderprvt";
    private static final String ORDER_BSN = "vlsmain?link=orderbsn";
    private static final String ABOUT = "vlsmain?link=about";
    //registration errors
    public static final String REGERR_SUCCESS = "Регистрация прошла успешно. На ваш почтовый адрес придёт письмо для завершения регистрации";
    public static final String REGERR_USEREXIST = "Пользователь с таким адресом e-mail уже существует.";
    public static final String REGERR_PSWMISMATCH = "Пароли не соответствуют.";
    public static final String REGERR_DBERROR = "Ошибка базы данных.";


    public static ArrayList<String> getFormCalcReq() {
        ArrayList<String> formCalc = new ArrayList<>();
        formCalc.add("<form action=\"vlscalc.do\" method=\"POST\">");
        formCalc.add("<h1>Лизинговый калькулятор</h1>");
        formCalc.add("<h2>Заполните форму и нажмите рассчитать</h2>");
        formCalc.add("<table>");
        formCalc.add("<tr>");
        formCalc.add("<th>Веберите валюту расчёта:</th>");
        formCalc.add("<td><select name=\"currency\">");
        formCalc.add("<option value=\"byn\">BYN</option>");
        formCalc.add("<option value=\"usd\">USD</option>");
        formCalc.add("<option value=\"eur\">EUR</option>");
        formCalc.add("<option value=\"rub\">RUB</option>");
        formCalc.add("</select>");
        formCalc.add("</td>");
        formCalc.add("</tr>");
        formCalc.add("<tr>");
        formCalc.add("<th>Тип предмета:</th>");
        formCalc.add("<td>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"car\" checked=\"checked\"/>Легковой автомобиль</br>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"lorry\"/>Грузовой транспорт до 3.5 тонн</br>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"truck\"/>Грузовой транспорт свыше 3.5 тонн</br>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"buildingmachines\"/>Строительная техника</br>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"farmingmachinery\"/>Сельскохозяйственная техника</br>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"equipment\"/>Оборудование</br>");
        formCalc.add("<input type=\"radio\" name=\"objecttype\" value=\"realestate\"/>Недвижимость");
        formCalc.add("</td>");
        formCalc.add("</tr>");
        formCalc.add("<tr>");
        formCalc.add("<th>Возраст предмета, лет:</th>");
        formCalc.add("<td><select name=\"age\">");
        formCalc.add("<option value=\"0\">новый</option>");
        formCalc.add("<option value=\"1\">1</option>");
        formCalc.add("<option value=\"2\">2</option>");
        formCalc.add("<option value=\"3\">3</option>");
        formCalc.add("<option value=\"4\">4</option>");
        formCalc.add("<option value=\"5\">5</option>");
        formCalc.add("<option value=\"6\">6</option>");
        formCalc.add("<option value=\"7\">7</option>");
        formCalc.add("<option value=\"8\">8</option>");
        formCalc.add("<option value=\"9\">9</option>");
        formCalc.add("<option value=\"10\">10</option>");
        formCalc.add("</select>");
        formCalc.add("</td>");
        formCalc.add("</tr>");
        formCalc.add("<tr>");
        formCalc.add("<th>Параметры расчёта: </th>");
        formCalc.add("<td>");
        formCalc.add("<table>");
        formCalc.add("<tr><td>Стоимость:</td><td><input type=\"text\" name=\"cost\" required=\"required\" pattern=\"^[ 0-9]+$\"/></td></tr>");
        formCalc.add("<tr><td>Без НДС:</td><td><input type=\"checkbox\" name=\"novatoncostflag\" value=\"1\"/></td></tr>");
        formCalc.add("<tr><td>Аванс:</td><td>");
        formCalc.add("<select name=\"prepay\">");
        formCalc.add("<option value=\"0.10\"/>10%</option>");
        formCalc.add("<option value=\"0.15\"/>15%</option>");
        formCalc.add("<option value=\"0.20\"/>20%</option>");
        formCalc.add("<option value=\"0.25\"/>25%</option>");
        formCalc.add("<option value=\"0.30\"/>30%</option>");
        formCalc.add("<option value=\"0.35\"/>35%</option>");
        formCalc.add("<option value=\"0.40\"/>40%</option>");
        formCalc.add("</td></tr>");
        formCalc.add("<tr><td>Срок:</td><td>");
        formCalc.add("<select name=\"duration\">");
        formCalc.add("<option value=\"12\"/>12 мес.</option>");
        formCalc.add("<option value=\"18\"/>18 мес.</option>");
        formCalc.add("<option value=\"24\"/>24 мес.</option>");
        formCalc.add("<option value=\"30\"/>30 мес.</option>");
        formCalc.add("<option value=\"36\"/>36 мес.</option>");
        formCalc.add("<option value=\"42\"/>42 мес.</option>");
        formCalc.add("<option value=\"48\"/>48 мес.</option>");
        formCalc.add("</td></tr>");
        formCalc.add("<tr><td>Выкупная стоимость:</td><td>");
        formCalc.add("<select name=\"byuingoutpercent\">");
        formCalc.add("<option value=\"0.0\"/>0.0%</option>");
        formCalc.add("<option value=\"0.0001\"/>0.01%</option>");
        formCalc.add("<option value=\"0.001\"/>0.1%</option>");
        formCalc.add("<option value=\"0.01\"/>1%</option>");
        formCalc.add("<option value=\"0.05\"/>5%</option>");
        formCalc.add("<option value=\"0.10\"/>10%</option>");
        formCalc.add("<option value=\"0.15\"/>15%</option>");
        formCalc.add("<option value=\"0.20\"/>20%</option>");
        formCalc.add("<tr><td>Включить страхование:</td><td><input type=\"checkbox\" name=\"showinsurancecol\" value=\"1\"/></td></tr>");
        formCalc.add("</td></tr></table>");
        formCalc.add("</td></tr>");
        formCalc.add("<tr><th></th>");
        formCalc.add("<td><input type=\"submit\" value=\"Рассчитать\"/></td>");
        formCalc.add("</tr>");
        formCalc.add("</table>");
        formCalc.add("</form>");
        return formCalc;
    }

    public static ArrayList<String> getAuthForm() {
        ArrayList<String> formAuth = new ArrayList<>();
        formAuth.add("<form action=\"vlsauth.do\" method=\"POST\">");
        formAuth.add("<p class=\"auth\">Логин:<input type=\"email\" name=\"login\" required=\"required\"/> Пароль: <input type=\"password\" name=\"password\" required=\"required\"/> <input type=\"submit\" value=\"Войти\"/></br>");
        formAuth.add("<a class=\"auth_reg\" href=\"register.do\">Зарегистрироваться</a>");
        formAuth.add("</p>");
        formAuth.add("</form>");
        return formAuth;
    }

    public static ArrayList<String> getRegForm(int errcode) {
        ArrayList<String> formReg = new ArrayList<>();
        formReg.add("<form action=\"register.do\" method=\"POST\">");
        formReg.add("<h1>Регистрационная форма</h1>");
        formReg.add("<h2>Заполните поля:</h2>");
        formReg.add("<table>");
        formReg.add("<tr>");
        formReg.add("<th>Тип клиента:</th>");
        formReg.add("<td>");
        formReg.add("<input type=\"radio\" name=\"usertype\" value=\"company\" checked=\"checked\"/>Юридическое лицо");
        formReg.add("<input type=\"radio\" name=\"usertype\" value=\"private\"/>Физическое лицо");
        formReg.add("</td>");
        formReg.add("</tr>");
        formReg.add("<tr><th>Адрес e-mail: </th><td><input type=\"email\" name=\"login\" required=\"required\" maxlength=\"30\"/></td></tr>");
        formReg.add("<tr><th>Пароль: </th><td><input type=\"password\" name=\"password1\" required=\"required\" pattern=\"[A-Za-z0-9]{8,20}\" title=\"8-20 букв и цифр латинского алфавита\" maxlength=\"20\"/></td></tr>");
        formReg.add("<tr><th>Пароль еще раз: </th><td><input type=\"password\" name=\"password2\" required=\"required\" pattern=\"[A-Za-z0-9]{8,20}\" maxlength=\"20\"/></td></tr>");
        formReg.add("<tr>");
        formReg.add("<th></th>");
        formReg.add("<td><input type=\"submit\" value=\"Зарегистрироваться\"/></td>");
        formReg.add("</tr>");
        formReg.add("</table>");
        formReg.add("</form>");
        if (errcode != -1) {
            switch (errcode) {
                case 0: {
                    formReg.add("<p class=\"error\">" + REGERR_SUCCESS + "</p>");
                    break;
                }
                case 1: {
                    formReg.add("<p class=\"error\">Ошибка: " + REGERR_USEREXIST + "</p>");
                    break;
                }
                case 2: {
                    formReg.add("<p class=\"error\">Ошибка: " + REGERR_PSWMISMATCH + "</p>");
                    break;
                }
                case 3: {
                    formReg.add("<p class=\"error\">Ошибка: " + REGERR_DBERROR + "</p>");
                    break;
                }
            }
        }


        return formReg;
    }

    public static ArrayList<String> getLogOutForm(String user) {
        ArrayList<String> formLogOut = new ArrayList<>();
        formLogOut.add("<p class=\"logout\">Вы авторизовались как: " + user + " ");
        formLogOut.add("<a class=\"logout\" href=\"vlsauth.do?auth=logout\">Выйти</a></p>");
        return formLogOut;
    }

    public static ArrayList<String> getMenu(int user_status) {

        ArrayList<String> formMenu = new ArrayList<>();
        formMenu.add("<p class=\"menu\">");
        formMenu.add("<a class=\"menu\" href=\"" + CALC + "\">Калькулятор</a>  ");
        switch (user_status) {
            case UserValidator.AUTH_USER_BSN: {
                formMenu.add("<a class=\"menu\" href=\"" + IDENT_BSN + "\">Идентификация</a>  ");
                formMenu.add("<a class=\"menu\" href=\"" + ORDER_BSN + "\">Online-заказ</a>  ");
                break;
            }
            case UserValidator.AUTH_USER_PRVT: {
                formMenu.add("<a class=\"menu\" href=\"" + IDENT_PRVT + "\">Идентификация</a>  ");
                formMenu.add("<a class=\"menu\" href=\"" + ORDER_PRVT + "\">Online-заказ</a>  ");
                break;
            }
            case UserValidator.AUTH_USER_BSN_CLNT: {
                formMenu.add("<a class=\"menu\" href=\"" + CABINET_BSN + "\">Кабинет</a>  ");
                break;
            }
            case UserValidator.AUTH_USER_PRVT_CLNT: {
                formMenu.add("<a class=\"menu\" href=\"" + CABINET_PRVT + "\">Кабинет</a>  ");
                break;
            }
        }
        formMenu.add("<a class=\"menu\" href=\"" + ABOUT + "\">О проекте</a>");
        formMenu.add("</p>");
        return formMenu;
    }

    public static ArrayList<String> getErrorForm(String text) {
        ArrayList<String> formLogOut = new ArrayList<>();
        formLogOut.add("<p class=\"error\">Сообщение: " + text + "</p>");
        return formLogOut;
    }

    public static ArrayList<String> getAboutForm() {
        ArrayList<String> formAbout = new ArrayList<String>();
        formAbout.add("<p class=\"about\">Автор проекта: Denis Vasilevsky</br> e-mail: 4denver@mail.ru</br>Skype: d.vasilevsky</p>");
        return formAbout;
    }

    public static ArrayList<String> getIdentPrvtForm(Person person) {
        ArrayList<String> formIdentPrvt = new ArrayList<>();
        formIdentPrvt.add("<form action=\"identperson.do\" method=\"POST\">\n");
        formIdentPrvt.add("<h1>Представьтесь, пожалуйста</h1>\n");
        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<th>Пол:</th>\n");
        formIdentPrvt.add("<td>\n");
        String checked_m, checked_w;
        checked_m = "";
        checked_w = "";
        if (person.getGender() != null && person.getGender().equals("жен")) checked_w = "checked";
        else checked_m = "checked";
        formIdentPrvt.add("<input type=\"radio\" name=\"gender\" value=\"муж\"" + checked_m + "/>Мужчина \n");
        formIdentPrvt.add("<input type=\"radio\" name=\"gender\" value=\"жен\"" + checked_w + "/>Женщина\n");
        formIdentPrvt.add("</td>\n");
        formIdentPrvt.add("<tr><th>Фамилия:</th><td><input type=\"text\" name=\"lastName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getLastName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Имя:</th><td><input type=\"text\" name=\"firstName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getFirstName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Отчество:</th><td><input type=\"text\" name=\"patronymicName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getPatronymicName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Адрес:</th><td>\n");
        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<tr><td>Индекс:</td><td><input type=\"text\" name=\"postalCode\" pattern=\"[0-9]{6}\" size=\"6\" maxlength=\"6\" placeholder=\"123456\" autocomplete=\"on\" value=\"" + person.getPostalCode() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Область:</td><td><select name=\"province\">\n");
        formIdentPrvt.add("\t\t\t<option value=\"\"/>-</option>\n");
        String selected_bre, selected_vit, selected_gom, selected_gro, selected_min, selected_mog;
        selected_bre = "";
        selected_vit = "";
        selected_gom = "";
        selected_gro = "";
        selected_min = "";
        selected_mog = "";
        if (person.getProvince() != null) {
            switch (person.getProvince()) {
                case "Брестская": {
                    selected_bre = "selected";
                    break;
                }
                case "Витебская": {
                    selected_vit = "selected";
                    break;
                }
                case "Гомельская": {
                    selected_gom = "selected";
                    break;
                }
                case "Гродненская": {
                    selected_gro = "selected";
                    break;
                }
                case "Минская": {
                    selected_min = "selected";
                    break;
                }
                case "Могилёвская": {
                    selected_mog = "selected";
                    break;
                }
            }
        }

        formIdentPrvt.add("\t\t\t<option value=\"Брестская\"" + selected_bre + "/>Брестская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Витебская\"" + selected_vit + "/>Витебская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Гомельская\"" + selected_gom + "/>Гомельская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Гродненская\"" + selected_gro + "/>Гродненская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Минская\"" + selected_min + "/>Минская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Могилёвская\"" + selected_mog + "/>Могилёвская</option>\n");
        formIdentPrvt.add("</td></tr>\n");
        formIdentPrvt.add("<tr><td>Район:</td><td><input type=\"text\" name=\"district\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getDistrict()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Город:</td><td><input type=\"text\" name=\"city\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" required maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getCity()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Улица:</td><td><input type=\"text\" name=\"street\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getStreet()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Дом:</td><td><input type=\"text\" name=\"building\" pattern=\"[0-9A-Za-zА-Яа-яЁё-/]{1,}\" maxlength=\"10\" size=\"6\" autocomplete=\"on\" value=\"" + replaceNull(person.getBuilding()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Квартира:</td><td><input type=\"text\" name=\"room\" pattern=\"[0-9A-Za-zА-Яа-яЁё-/]{1,}\" maxlength=\"10\" size=\"6\" autocomplete=\"on\" value=\"" + replaceNull(person.getRoom()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</td></tr>\n");
        formIdentPrvt.add("<tr><th>Дата рождения:</th><td><input type=\"text\" name=\"birthDate\" required pattern=\"\\d{2}.\\d{2}.\\d{4}\" size=\"20\" maxlength=\"10\" placeholder=\"01.01.1990\" title=\"введите дату в формате дд.мм.гггг\" autocomplete=\"on\" value=\"" + replaceNull(person.getBirthDate()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Номер телефона:</th><td><input type=\"text\" name=\"phoneNumber\" required maxlength=\"20\" placeholder=\"375296123456\" title=\"введине свой контактный номер телефона\" autocomplete=\"on\" value=\"" + replaceNull(person.getPhoneNumber()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Работодатель:</th><td><input type=\"text\" name=\"employer\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getEmployer()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Должность:</th><td><input type=\"text\" name=\"position\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(person.getPosition()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Среднемесячный доход, бел.руб.:</th><td><input type=\"text\" name=\"monthlyIncome\" required pattern=\"\\d{2,6}\" size=\"20\" maxlength=\"6\" autocomplete=\"on\" value=\"" + person.getMonthlyIncome() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th></th><td><input type=\"submit\" value=\"Сохранить\"/></td></tr>");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</form>\n");
        return formIdentPrvt;
    }

    public static ArrayList<String> getOrderPrvtForm() {
        ArrayList<String> formOrderPrvt = new ArrayList<>();
        formOrderPrvt.add("<p class=\"order\">Order form for private person</p>");
        return formOrderPrvt;
    }

    public static ArrayList<String> getOrderBsnForm() {
        ArrayList<String> formOrderBsn = new ArrayList<>();
        formOrderBsn.add("<p class=\"order\">Order form for business</p>");
        return formOrderBsn;
    }

    public static ArrayList<String> getCalcListForm(CalcRequest calcreq) {
        ArrayList<String> formCalcList = new ArrayList<>();
        formCalcList.add("<h1>Расчёт лизинговых платежей</h1>");
        //parameters of calc
        formCalcList.add("<table class=\"list_header\">");
        formCalcList.add("<tr><th>Предмет лизинга:</th><td>" + calcreq.getObjectType() + "</td></tr>");
        formCalcList.add("<tr><th>Контрактная стоимость:</th><td>" + String.format("%9.2f", calcreq.getSumCost()) + "</td></tr>");
        formCalcList.add("<tr><th>НДС на стоимость:</th><td>" + String.format("%9.2f", calcreq.getSumCostVat()) + "</td></tr>");
        formCalcList.add("<tr><th>Контрактная стоимость предмета лизинга с НДС:</th><td>" + String.format("%9.2f", calcreq.getCost()) + "</td></tr>");
        formCalcList.add("<tr><th>Авансовый платеж: " + String.format("%4.1f", (calcreq.getPrepay()) * 100) + "%</th>");
        formCalcList.add("<td>" + String.format("%9.2f", calcreq.getPrepay() * calcreq.getCost()) + "</td></tr>");
        formCalcList.add("<tr><th>Валюта графика:</th><td>" + calcreq.getCurrency() + "</td></tr>");
        formCalcList.add("</table>");
        //header of calc-list
        formCalcList.add("<table class=\"list_calc\">");
        formCalcList.add("<tr>");
        formCalcList.add("<td rowspan=\"2\" class=\"colnames\">Месяц</td>");
        formCalcList.add("<td rowspan=\"2\" class=\"colnames\">Период</td>");
        formCalcList.add("<td rowspan=\"2\" class=\"colnames\">Непогашенная стоимость  на нач.периода</td>");
        formCalcList.add("<td colspan=\"2\" class=\"colnames\">Лизинговые платежи без НДС, в том числе</td>");
        formCalcList.add("<td colspan=\"2\" class=\"colnames\">НДС, в том числе</td>");
        formCalcList.add("<td rowspan=\"2\" class=\"colnames\">Страховка (справочно)</td>");
        formCalcList.add("<td rowspan=\"2\" class=\"colnames\">Лизинговые платежи с НДС (гр.4+гр.5+ гр.6+гр.7)</td>");
        formCalcList.add("</tr>");
        formCalcList.add("<tr>");
        formCalcList.add("<td class=\"colnames\">Лизинговая ставка</td>");
        formCalcList.add("<td class=\"colnames\">Стоимость без НДС </td>");
        formCalcList.add("<td class=\"colnames\">НДС на лизинговую ставку (гр.4*20%)</td>");
        formCalcList.add("<td class=\"colnames\">НДС на стоимость (гр.5*20%)</td>");
        formCalcList.add("</tr>");
        formCalcList.add("<tr>");
        formCalcList.add("<td class=\"colnames\">1</td><td class=\"colnames\">2</td><td class=\"colnames\">3</td><td class=\"colnames\">4</td>");
        formCalcList.add("<td class=\"colnames\">5</td><td class=\"colnames\">6</td><td class=\"colnames\">7</td><td class=\"colnames\">8</td><td class=\"colnames\">9</td>");
        formCalcList.add("</tr>");
        formCalcList.add("<tr>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td>Аванс:</td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getCost()) + "</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getPrepaySummNoVat()) + "</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getPrepayVat()) + "</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", (calcreq.getPrepaySummNoVat() + calcreq.getPrepayVat())) + "</td>");
        formCalcList.add("</tr>");
        //generate main table of pyments
        ArrayList tablerow = new ArrayList();
        for (int i = 0; i < calcreq.getDuration(); i++) {
            formCalcList.add("<tr><td>" + (i + 1) + "</td>");
            tablerow = calcreq.getRow(i);
            for (int j = 0; j < 8; j++) {
                if (j == 0) formCalcList.add("<td>" + tablerow.get(j) + "</td>");
                else formCalcList.add(String.format("<td class=\"floatnums\">%9.2f</td>", tablerow.get(j)));
            }
            formCalcList.add("</tr>");
        }
        //footer of calclist
        formCalcList.add("<tr>");
        formCalcList.add("<td colspan=\"3\">Итого:</td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalMargin()) + "</td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalCost()) + "</td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalMarginVat()) + "</td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalCostVat()) + "</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalWholePay()) + "</td>");
        formCalcList.add("</tr>");
        formCalcList.add("<tr>");
        formCalcList.add("<td colspan=\"3\">Выкупная стоимость:</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getByuOutNoVat()) + "</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", calcreq.getByuOutVat()) + "</td>");
        formCalcList.add("<td></td>");
        formCalcList.add("<td class=\"floatnums\">" + String.format("%9.2f", (calcreq.getByuOutNoVat() + calcreq.getByuOutVat())) + "</td>");
        formCalcList.add("</tr>");
        formCalcList.add("<tr>");
        formCalcList.add("<th colspan=\"3\">ВСЕГО:</td>");
        formCalcList.add("<th class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalMargin()) + "</td>");
        formCalcList.add("<th class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalCost() + calcreq.getByuOutNoVat()) + "</td>");
        formCalcList.add("<th class=\"floatnums\">" + String.format("%9.2f", calcreq.getTotalMarginVat()) + "</td>");
        formCalcList.add("<th class=\"floatnums\">" + String.format("%9.2f", calcreq.getByuOutVat() + calcreq.getTotalCostVat()) + "</td>");
        formCalcList.add("<th></td>");
        formCalcList.add("<th class=\"floatnums\">" + String.format("%9.2f", calcreq.getSumWholePay()) + "</td>");
        formCalcList.add("</tr>");
        formCalcList.add("</table>");
        return formCalcList;
    }

    public static String replaceNull(String in_string) {
        String out_string = "";
        if (in_string != null && !in_string.equals("null"))
            return in_string;
        else return out_string;
    }

    public static ArrayList<String> getIdentBsnForm(Company company) {
        ArrayList<String> formIdentPrvt = new ArrayList<>();
        formIdentPrvt.add("<form action=\"identcompany.do\" method=\"POST\">\n");
        formIdentPrvt.add("<h1>Представьте вашу компанию</h1>\n");

        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<tr><th>Название:</th><td><input type=\"text\" name=\"compName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCompName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Форма собственности:</th><td><input type=\"text\" name=\"compForm\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCompForm()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Дата регистрации:</th><td><input type=\"text\" name=\"compRegistrationDate\" required pattern=\"\\d{2}.\\d{2}.\\d{4}\" size=\"20\" maxlength=\"10\" placeholder=\"01.01.1990\" title=\"введите дату в формате дд.мм.гггг\" autocomplete=\"on\" value=\"" + replaceNull(company.getCompRegistrationDate()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Вид деятельности:</th><td><input type=\"text\" name=\"businessType\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getBusinessType()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Номер телефона:</th><td><input type=\"text\" name=\"phoneNumber\" required maxlength=\"20\" placeholder=\"375296123456\" title=\"введине свой контактный номер телефона\" autocomplete=\"on\" value=\"" + replaceNull(company.getPhoneNumber()) + "\"" + "/></td></tr>\n");

        formIdentPrvt.add("<tr><th>Адрес офиса:</th><td>\n");
        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<tr><td>Индекс:</td><td><input type=\"text\" name=\"postalCode\" pattern=\"[0-9]{6}\" size=\"6\" maxlength=\"6\" placeholder=\"123456\" autocomplete=\"on\" value=\"" + company.getPostalCode() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Область:</td><td><select name=\"province\">\n");
        formIdentPrvt.add("\t\t\t<option value=\"\"/>-</option>\n");
        String selected_bre, selected_vit, selected_gom, selected_gro, selected_min, selected_mog;
        selected_bre = "";
        selected_vit = "";
        selected_gom = "";
        selected_gro = "";
        selected_min = "";
        selected_mog = "";
        if (company.getProvince() != null) {
            switch (company.getProvince()) {
                case "Брестская": {
                    selected_bre = "selected";
                    break;
                }
                case "Витебская": {
                    selected_vit = "selected";
                    break;
                }
                case "Гомельская": {
                    selected_gom = "selected";
                    break;
                }
                case "Гродненская": {
                    selected_gro = "selected";
                    break;
                }
                case "Минская": {
                    selected_min = "selected";
                    break;
                }
                case "Могилёвская": {
                    selected_mog = "selected";
                    break;
                }
            }
        }
        formIdentPrvt.add("\t\t\t<option value=\"Брестская\"" + selected_bre + "/>Брестская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Витебская\"" + selected_vit + "/>Витебская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Гомельская\"" + selected_gom + "/>Гомельская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Гродненская\"" + selected_gro + "/>Гродненская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Минская\"" + selected_min + "/>Минская</option>\n");
        formIdentPrvt.add("\t\t\t<option value=\"Могилёвская\"" + selected_mog + "/>Могилёвская</option>\n");
        formIdentPrvt.add("</td></tr>\n");
        formIdentPrvt.add("<tr><td>Район:</td><td><input type=\"text\" name=\"district\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getDistrict()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Город:</td><td><input type=\"text\" name=\"city\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" required maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCity()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Улица:</td><td><input type=\"text\" name=\"street\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getStreet()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Дом:</td><td><input type=\"text\" name=\"building\" pattern=\"[0-9A-Za-zА-Яа-яЁё-/]{1,}\" maxlength=\"10\" size=\"6\" autocomplete=\"on\" value=\"" + replaceNull(company.getBuilding()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Помещение:</td><td><input type=\"text\" name=\"room\" pattern=\"[0-9A-Za-zА-Яа-яЁё-/]{1,}\" maxlength=\"10\" size=\"6\" autocomplete=\"on\" value=\"" + replaceNull(company.getRoom()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</td></tr>\n");

        formIdentPrvt.add("<tr><th>Среднемесячная выручка, бел.руб.:</th><td><input type=\"text\" name=\"monthlyIncome\" required pattern=\"\\d{2,8}\" size=\"20\" maxlength=\"8\" autocomplete=\"on\" value=\"" + company.getMonthlyIncome() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><th>Количество сотрудников:</th><td><input type=\"text\" name=\"numberOfStaff\" required pattern=\"\\d{1,6}\" size=\"20\" maxlength=\"6\" autocomplete=\"on\" value=\"" + company.getNumberOfStaff() + "\"" + "/></td></tr>\n");

        formIdentPrvt.add("<tr><th>Основные средства:</th><td>\n");
        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<tr><td>Легковой автомобиль, ед.:</td><td><input type=\"text\" name=\"numberOfCars\" required pattern=\"\\d{1,3}\" size=\"4\" maxlength=\"5\" autocomplete=\"on\" value=\"" + company.getNumberOfCars() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Груз. транспорт до 3.5т, ед.:</td><td><input type=\"text\" name=\"numberOfLorrys\" required pattern=\"\\d{1,3}\" size=\"4\" maxlength=\"3\" autocomplete=\"on\" value=\"" + company.getNumberOfLorrys() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Груз. транспорт свыше  3.5т, ед.:</td><td><input type=\"text\" name=\"numberOfTrucks\" required pattern=\"\\d{1,3}\" size=\"4\" maxlength=\"3\" autocomplete=\"on\" value=\"" + company.getNumberOfTrucks() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Строительная техника, ед.:</td><td><input type=\"text\" name=\"numberOfBuildMach\" required pattern=\"\\d{1,3}\" size=\"4\" maxlength=\"3\" autocomplete=\"on\" value=\"" + company.getNumberOfBuildMach() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Сель-хоз. техника, ед.:</td><td><input type=\"text\" name=\"numberOfFarmMach\" required pattern=\"\\d{1,3}\" size=\"4\" maxlength=\"3\" autocomplete=\"on\" value=\"" + company.getNumberOfFarmMach() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Оборудование, линий:</td><td><input type=\"text\" name=\"numberOfEquipmentLines\" required pattern=\"\\d{1,3}\" size=\"4\" maxlength=\"3\" autocomplete=\"on\" value=\"" + company.getNumberOfEquipmentLines() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Офисные площади, м.кв.:</td><td><input type=\"text\" name=\"numberOfOfficeArea\" required pattern=\"\\d{1,5}\" size=\"4\" maxlength=\"5\" autocomplete=\"on\" value=\"" + company.getNumberOfOfficeArea() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Прочие площади, м.кв.:</td><td><input type=\"text\" name=\"numberOfOtherArea\" required pattern=\"\\d{1,5}\" size=\"4\" maxlength=\"5\" autocomplete=\"on\" value=\"" + company.getNumberOfOtherArea() + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</td></tr>\n");

        formIdentPrvt.add("<tr><th>Руководитель:</th><td>\n");
        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<tr><td>Должность:</td><td><input type=\"text\" name=\"ceoPosition\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCeoPosition()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Фамилия:</td><td><input type=\"text\" name=\"ceoLastName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCeoLastName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Имя:</td><td><input type=\"text\" name=\"ceoFirstName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCeoFirstName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Отчество:</td><td><input type=\"text\" name=\"ceoPatronymicName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getCeoPatronymicName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Номер телефона:</td><td><input type=\"text\" name=\"ceoPhoneNumber\" maxlength=\"20\" placeholder=\"375296123456\" title=\"введине свой контактный номер телефона\" autocomplete=\"on\" value=\"" + replaceNull(company.getCeoPhoneNumber()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</td></tr>\n");

        formIdentPrvt.add("<tr><th>Контактное лицо:</th><td>\n");
        formIdentPrvt.add("<table>\n");
        formIdentPrvt.add("<tr><td>Должность:</td><td><input type=\"text\" name=\"contactPersonPosition\" pattern=\"[0-9A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getContactPersonPosition()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Фамилия:</td><td><input type=\"text\" name=\"contactPersonLastName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getContactPersonLastName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Имя:</td><td><input type=\"text\" name=\"contactPersonFirstName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getContactPersonFirstName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Отчество:</td><td><input type=\"text\" name=\"contactPersonPatronymicName\" required pattern=\"[A-Za-zА-Яа-яЁё- ]{2,}\" maxlength=\"30\" autocomplete=\"on\" value=\"" + replaceNull(company.getContactPersonPatronymicName()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("<tr><td>Номер телефона:</td><td><input type=\"text\" name=\"contactPersonPhoneNumber\" required maxlength=\"20\" placeholder=\"375296123456\" title=\"введине свой контактный номер телефона\" autocomplete=\"on\" value=\"" + replaceNull(company.getContactPersonPhoneNumber()) + "\"" + "/></td></tr>\n");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</td></tr>\n");

        formIdentPrvt.add("<tr><th></th><td><input type=\"submit\" value=\"Сохранить\"/></td></tr>");
        formIdentPrvt.add("</table>\n");
        formIdentPrvt.add("</form>\n");
        return formIdentPrvt;
    }
}