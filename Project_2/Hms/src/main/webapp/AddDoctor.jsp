<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Doctor</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-green-100 via-blue-100 to-purple-100 min-h-screen flex items-center justify-center p-8">
    
    <jsp:include page="navbar.jsp"/>

    <h:form styleClass="w-full max-w-3xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">
        <h2 class="text-4xl font-extrabold mb-8 text-blue-700 drop-shadow-lg text-center">
            Add New Doctor
        </h2>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <!-- Doctor ID -->
           <!--  <div>
                <h:outputLabel for="doctorId" value="Doctor ID:" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="doctorId" value="#{hospitalController.doctorDetails.doctorId}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>  -->

            <!-- Doctor Name -->
            <div>
                <h:outputLabel for="doctorName" value="Name:" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="doctorName" value="#{hospitalController.doctorDetails.doctorName}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>

            <!-- Specialization -->
            <div>
                <h:outputLabel for="specialization" value="Specialization:" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:selectOneMenu id="specialization" value="#{hospitalController.doctorDetails.specialization}"
                                 styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2">
                    <f:selectItem itemLabel="-- Select Specialization --" itemValue="" />
                    <f:selectItem itemLabel="HEART" itemValue="HEART" />
                    <f:selectItem itemLabel="SKIN" itemValue="SKIN" />
                    <f:selectItem itemLabel="GENERAL" itemValue="GENERAL" />
                    <f:selectItem itemLabel="KIDNEY" itemValue="KIDNEY" />
                </h:selectOneMenu>
            </div>

            <!-- Location -->
            <div>
                <h:outputLabel for="location" value="Location:" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="location" value="#{hospitalController.doctorDetails.location}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>

            <!-- Mobile Number -->
            <div class="md:col-span-2">
                <h:outputLabel for="mobileNo" value="Mobile Number:" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="mobileNo" value="#{hospitalController.doctorDetails.mobNo}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>
        </div>

        <!-- Submit Button -->
        <div class="mt-8 text-center">
            <h:commandButton value="Add Doctor" action="#{hospitalController.addDoctor}"
                             styleClass="bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 px-8 rounded-lg shadow-md transition duration-300" />
        </div>
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
