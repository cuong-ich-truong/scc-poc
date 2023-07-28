```plantuml
@startuml Security Command Center Context Diagram
    ' https://github.com/plantuml-stdlib/C4-PlantUML#including-the-c4-plantuml-library
    !include <c4/C4_Context.puml>
    !include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Context.puml

    LAYOUT_WITH_LEGEND()

    title Security Command Center Context Diagram

    Person(operator, "Operator", "Operator works in the operations center")
    Person(guard, "Security Guard", "Security Guards patrolling the facility")

    Boundary(backend, "SCC System") {
      System(sccApp, "SCC Application", "Security Command Center Application")
      System(alertSys, "Alert System", "Alert System")
      System_Ext(cctv, "CCTV", "Closed Circuit Television")
    }
    System(phone, "Phone", "Mobile App")

    Rel(cctv, sccApp, "Sends Video Feed")
    Rel(alertSys, sccApp, "Sends Alerts")
    Rel(sccApp, phone, "Sends Alert Notifications")
    Rel(phone, sccApp, "Sends Incident Reports")

    Rel(operator, sccApp, "Monitor CCTV, View and Assign Alerts")
    Rel(guard, phone, "Acknowledge Alerts / Report Incidents")   
@enduml
```
