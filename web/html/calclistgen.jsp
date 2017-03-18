<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,by.vls.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="ru" xml:lang="ru">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Calculator form</title>
	<link rel="stylesheet" type="text/css" href="vls.css" />
	</head>
<body>
<p class="header">Virtual Leasing Specialist</p>
<h1>Расчёт лизинговых платежей</h1>
    <%CalcRequest calcreq = (CalcRequest)request.getAttribute("calclist");%>
<table class="list_header">
<tr>
<th>Предмет лизинга:</th>
<td><%out.print(calcreq.getObjectType());%></td>
</tr>
<tr>
<th>Контрактная стоимость:</th>
<td><%out.print(String.format("%9.2f", calcreq.getSumCost()));%></td>
</tr>
<tr>
<th>НДС на стоимость:</th>
<td><%out.print(String.format("%9.2f", calcreq.getSumCostVat()));%></td>
</tr>
<tr>
<th>Контрактная стоимость предмета лизинга с НДС:</th>
<td><%out.print(String.format("%9.2f", calcreq.getCost()));%></td>
</tr>
<tr>
<th>Авансовый платеж: <%out.print(String.format("%4.1f", (calcreq.getPrepay())*100));%>%</th>
<td><%out.print(String.format("%9.2f", calcreq.getPrepay()*calcreq.getCost()));%></td>
</tr>
<tr>
<th>Валюта графика:</th>
<td><%out.print(calcreq.getCurrency());%></td>
</tr>
</table>
<table class="list_calc">
<tr>
<td rowspan="2" class="colnames">Месяц</td>
<td rowspan="2" class="colnames">Период</td>
<td rowspan="2" class="colnames">Непогашенная стоимость  на нач.периода</td>
<td colspan="2" class="colnames">Лизинговые платежи без НДС, в том числе</td>
<td colspan="2" class="colnames">НДС, в том числе</td>
<td rowspan="2" class="colnames">Страховка (справочно)</td>
<td rowspan="2" class="colnames">Лизинговые платежи с НДС (гр.4+гр.5+ гр.6+гр.7)</td>
</tr>
<tr>
<td class="colnames">Лизинговая ставка</td>
<td class="colnames">Стоимость без НДС </td>
<td class="colnames">НДС на лизинговую ставку (гр.4*20%)</td>
<td class="colnames">НДС на стоимость (гр.5*20%)</td>
<tr>
<tr>
<td class="colnames">1</td><td class="colnames">2</td><td class="colnames">3</td><td class="colnames">4</td>
<td class="colnames">5</td><td class="colnames">6</td><td class="colnames">7</td><td class="colnames">8</td><td class="colnames">9</td>
</tr>
<tr>
<td></td>
<td>Аванс:</td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getCost()));%></td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getPrepaySummNoVat()));%></td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getPrepayVat()));%></td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f", (calcreq.getPrepaySummNoVat()+calcreq.getPrepayVat())));%></td>
</tr>
<%
ArrayList tablerow = new ArrayList();
for (int i=0; i<calcreq.getDuration(); i++){
	out.print("<tr><td>"+(i+1)+"</td>");
	tablerow = calcreq.getRow(i);
	for (int j=0; j<8; j++){
		if (j==0) out.print("<td>"+tablerow.get(j)+"</td>");
		else out.print(String.format("<td class=\"floatnums\">%9.2f</td>", tablerow.get(j)));
	}
	out.print("</tr>");
	
}

%>
<tr>
<td colspan="3">Итого:</td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalMargin()));%></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalCost()));%></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalMarginVat()));%></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalCostVat()));%></td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalWholePay()));%></td>
</tr>
<tr>
<td colspan="3">Выкупная стоимость:</td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getByuOutNoVat()));%></td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f", calcreq.getByuOutVat()));%></td>
<td></td>
<td class="floatnums"><%out.print(String.format("%9.2f",(calcreq.getByuOutNoVat()+calcreq.getByuOutVat())));%></td>
</tr>
<tr>
<th colspan="3">ВСЕГО:</td>
<th class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalMargin()));%></td>
<th class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalCost()+calcreq.getByuOutNoVat()));%></td>
<th class="floatnums"><%out.print(String.format("%9.2f", calcreq.getTotalMarginVat()));%></td>
<th class="floatnums"><%out.print(String.format("%9.2f", calcreq.getByuOutVat()+calcreq.getTotalCostVat()));%></td>
<th></td>
<th class="floatnums"><%out.print(String.format("%9.2f",calcreq.getSumWholePay()));%></td>
</tr>

</table>

<%HashMap mysqlconfig = (HashMap)getServletContext().getAttribute("configcalc");
mysqlconfig = (HashMap)mysqlconfig.get("baseRate");
 
out.print("<h1> VLS Context: "+mysqlconfig.get("usd")+"</h1>");

%>

<h1>URL from JSP: <%out.print(getServletContext().getInitParameter("url"));%></h1>

</body>
</html>