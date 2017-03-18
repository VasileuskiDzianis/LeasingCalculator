<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,by.vls.model.*,by.vls.web.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="ru" xml:lang="ru">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Virtual Leasing Specialist</title>
	<link rel="stylesheet" type="text/css" href="vls.css" />
	</head>
<body>
	<p class="header">Virtual Leasing Specialist</p>
	
	
	
    <%
	
	HashMap mainconfig = (HashMap)request.getAttribute("mainconfig");
	
	if  ((Integer)mainconfig.get("showauthform")==1){
	Iterator it = FormCollection.getAuthForm().iterator();
        while (it.hasNext()) out.print(it.next());
	}
	if  ((String)mainconfig.get("showlogoutform")!=null){
	Iterator it = FormCollection.getLogOutForm((String)mainconfig.get("showlogoutform")).iterator();
        while (it.hasNext()) out.print(it.next());
	}
	
	if  ((Integer)mainconfig.get("showregform")==1){
	Iterator it = FormCollection.getRegForm().iterator();
        while (it.hasNext()) out.print(it.next());
	}
	
	if  ((Integer)mainconfig.get("showcalcform")==1){
	Iterator it = FormCollection.getFormCalcReq().iterator();
        while (it.hasNext()) out.print(it.next());
	}
	if  ((Integer)mainconfig.get("error")==1){
	out.print("<p>Error: "+mainconfig.get("errortext")+"</p>");
	}
	
	%>

</body>
</html>