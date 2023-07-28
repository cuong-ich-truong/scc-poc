```plantuml
@startuml Security Command Center Context Diagram
    ' https://github.com/plantuml-stdlib/C4-PlantUML#including-the-c4-plantuml-library
    !include <c4/C4_Context.puml>
    !include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Context.puml

    LAYOUT_WITH_LEGEND()

    title Security Command Center Context Diagram

    Person(operator, "Operator", "Operator works in the operations center")
    Person(guard, "Security Guard", "Security Guards patrolling the facility")

    System_Ext(cctv, "CCTV", "Multiple closed-circuit television (CCTV) cameras")
    System(scc, "Security Command Center System", "Allow users to monitor and respond to alerts")

    Rel(cctv, scc, "Sends Video Feed")
    Rel_Left(operator, scc, "Monitor CCTV, View and Assign Alerts")
    Rel_Right(guard, scc, "Acknowledge Alerts / Report Incidents")   
@enduml
```
