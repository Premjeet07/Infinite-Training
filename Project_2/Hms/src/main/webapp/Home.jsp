<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <title>Hospital Management System - Home</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
</head>
<body class="bg-gradient-to-r from-indigo-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">
	<jsp:include page="navbar.jsp"/>
	
    <h:form styleClass="bg-white p-12 rounded-3xl shadow-2xl max-w-4xl w-full text-center animate-fadeIn mt-20">

        <h1 class="text-5xl font-extrabold mb-12 text-indigo-700 drop-shadow-lg">
            Hospital Management System
        </h1>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-10">

            <h:commandLink action="AddDoctor" styleClass="group block bg-indigo-600 text-white rounded-xl shadow-lg p-8 transform transition duration-300 hover:-translate-y-2 hover:shadow-2xl">
                <i class="fas fa-user-md text-6xl mb-6 group-hover:text-pink-400 transition"></i>
                <h2 class="text-2xl font-bold mb-2">Add Doctor</h2>
                <p class="opacity-80 group-hover:opacity-100 transition">Add a new doctor to the system.</p>
            </h:commandLink>

            <h:commandLink action="DoctorList" styleClass="group block bg-purple-600 text-white rounded-xl shadow-lg p-8 transform transition duration-300 hover:-translate-y-2 hover:shadow-2xl">
                <i class="fas fa-list-ul text-6xl mb-6 group-hover:text-yellow-400 transition"></i>
                <h2 class="text-2xl font-bold mb-2">Show Doctor List</h2>
                <p class="opacity-80 group-hover:opacity-100 transition">View all doctors registered.</p>
            </h:commandLink>

            <h:commandLink action="showByCategory" styleClass="group block bg-pink-600 text-white rounded-xl shadow-lg p-8 transform transition duration-300 hover:-translate-y-2 hover:shadow-2xl">
                <i class="fas fa-filter text-6xl mb-6 group-hover:text-indigo-400 transition"></i>
                <h2 class="text-2xl font-bold mb-2">Show By Category</h2>
                <p class="opacity-80 group-hover:opacity-100 transition">Filter doctors by specialization.</p>
            </h:commandLink>

            <h:commandLink action="SearchDoctorById" styleClass="group block bg-yellow-500 text-white rounded-xl shadow-lg p-8 transform transition duration-300 hover:-translate-y-2 hover:shadow-2xl">
                <i class="fas fa-search text-6xl mb-6 group-hover:text-indigo-600 transition"></i>
                <h2 class="text-2xl font-bold mb-2">Search By Doctor ID</h2>
                <p class="opacity-80 group-hover:opacity-100 transition">Search doctor details by ID.</p>
            </h:commandLink>
            
           <h:panelGroup rendered="#{not empty flash.successMessage}">
			    <h:outputText value="#{flash.successMessage}" 
			                  styleClass="bg-green-100 text-green-800 border border-green-300 px-4 py-3 rounded mb-4 shadow-md text-center text-lg" />
			</h:panelGroup>
        </div>
    </h:form>

    <style>
        @keyframes fadeIn {
            0% {opacity: 0; transform: translateY(-20px);}
            100% {opacity: 1; transform: translateY(0);}
        }
        .animate-fadeIn {
            animation: fadeIn 1s ease forwards;
        }
    </style>

</body>
</html>
</f:view>
