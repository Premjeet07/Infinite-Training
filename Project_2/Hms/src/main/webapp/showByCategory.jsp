<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show Doctors by Category</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-blue-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">
	<jsp:include page="navbar.jsp"/>
    <h:form styleClass="w-full max-w-5xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">

        <h2 class="text-4xl font-extrabold mb-8 text-indigo-700 drop-shadow-lg text-center">
            Filter Doctors by Specialization
        </h2>

        <!-- Category selection 'HEART','SKIN','GENERAL','KIDNEY' -->
        <div class="mb-6 text-center">
            <h:outputLabel for="category" value="Select Specialization: " styleClass="text-lg font-semibold mr-4" />
            <h:selectOneMenu id="category" value="#{hospitalController.selectedCategory}" 
                             styleClass="border rounded-lg px-4 py-2 shadow-sm focus:ring-indigo-500 focus:border-indigo-500">
                <f:selectItem itemLabel="-- Select Category --" itemValue="" />
                <f:selectItem itemLabel="HEART" itemValue="HEART" />
                <f:selectItem itemLabel="SKIN" itemValue="SKIN" />
                <f:selectItem itemLabel="GENERAL" itemValue="GENERAL" />
                <f:selectItem itemLabel="KIDNEY" itemValue="KIDNEY" />
                <!-- Add more as needed -->
            </h:selectOneMenu>

            <h:commandButton value="Show Doctors" action="#{hospitalController.filterByCategory}" 
                             styleClass="ml-4 bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-2 rounded-lg shadow-md transition duration-300" />
        </div>

        <!-- Table to show filtered doctors -->
        <h:dataTable value="#{hospitalController.filteredDoctors}" var="d" border="0"
                     styleClass="min-w-full divide-y divide-gray-200 rounded-lg overflow-hidden shadow-lg"
                     headerClass="bg-indigo-600 text-white uppercase text-sm font-semibold"
                     rowClasses="bg-white odd:bg-indigo-50 hover:bg-indigo-100 transition-colors duration-300">
            <h:column>
                <f:facet name="header">
                    <h:outputLabel value="Doctor ID" styleClass="px-6 py-3 text-left" />
                </f:facet>
                <h:outputText value="#{d.doctorId}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputLabel value="Name" styleClass="px-6 py-3 text-left" />
                </f:facet>
                <h:outputText value="#{d.doctorName}" styleClass="px-6 py-4 whitespace-nowrap text-gray-800 font-semibold" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputLabel value="Specialization" styleClass="px-6 py-3 text-left" />
                </f:facet>
                <h:outputText value="#{d.specialization}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputLabel value="Location" styleClass="px-6 py-3 text-left" />
                </f:facet>
                <h:outputText value="#{d.location}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputLabel value="Mobile Number" styleClass="px-6 py-3 text-left" />
                </f:facet>
                <h:outputText value="#{d.mobNo}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
            </h:column>
        </h:dataTable>

    </h:form>

    <style>
        @keyframes fadeIn {
            0% { opacity: 0; transform: translateY(-10px); }
            100% { opacity: 1; transform: translateY(0); }
        }
        .animate-fadeIn {
            animation: fadeIn 0.8s ease forwards;
        }
    </style>

</body>
</html>
</f:view>
