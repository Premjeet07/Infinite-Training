<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 
<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Doctor Records</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-indigo-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">
	<jsp:include page="navbar.jsp"/>
    <h:form styleClass="w-full max-w-6xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">

        <h2 class="text-4xl font-extrabold mb-10 text-indigo-700 drop-shadow-lg text-center">
            Doctor Records
        </h2>

        <div class="overflow-x-auto rounded-xl shadow-lg">
            <h:dataTable value="#{hospitalController.showDoctor()}" var="d" border="0"
                styleClass="min-w-full divide-y divide-gray-200 rounded-lg overflow-hidden"
                headerClass="bg-indigo-600 text-white uppercase text-sm font-semibold"
                rowClasses="bg-white odd:bg-indigo-50 hover:bg-indigo-100 transition-colors duration-300">

                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Doctor Id" styleClass="px-6 py-3 text-left" />
                    </f:facet>
                    <h:outputText value="#{d.doctorId}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Doctor Name" styleClass="px-6 py-3 text-left" />
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
        </div>

    </h:form>

    <style>
        @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(-20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .animate-fadeIn {
            animation: fadeIn 1s ease forwards;
        }
    </style>

</body>
</html>
</f:view>
