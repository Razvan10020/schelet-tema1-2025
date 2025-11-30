# Tema 1 POO  - TerraBot

<div align="center"><img src="https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExdWZxaTdmdTFoczU5ZW90eTFsN2FwMG5lbDl5dDl5MHBucTB1a2NnZCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/voirD51GFZte0/giphy.gif" width="500px"></div>

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema)

## Tests

1. test01_initialize_entities 3p
   Entities were initialized by parsing JSON input, ensuring correct object creation and property assignment based on specified types and values.
2. test02_initialize_entities_errors - 2p
   Error handling for entity initialization involved validating input data and gracefully managing invalid or missing parameters, preventing unexpected program termination.
3. test03_move_robot - 5p
   Robot movement was implemented using pathfinding algorithms, updating coordinates and checking for valid terrain and obstacles. State changes were carefully tracked.
4. test04_move_robot_errors – 2p
   Movement error tests verified the robot's behavior when encountering invalid moves, out-of-bounds attempts, or impassable terrain, ensuring robust error reporting.
5. test05_env_condition - 2p
   Environmental conditions were managed through state variables, updating based on predefined rules and external inputs to influence entity interactions and behavior.
6. test06_update_battery - 3p
   Battery updates involved tracking energy consumption during robot actions and environmental interactions, decrementing levels and triggering low-power states as needed.
7. test07_update_battery_errors 2p
   Battery error handling focused on scenarios like negative consumption or attempting actions with insufficient power, ensuring the system remains stable and reports issues.
8. test08_change_weather - 3p
   Weather changes were modeled by updating global environment parameters, affecting various entity attributes like plant growth rates or animal activity levels accordingly.
9. test09_scan_plant 3p
   Plant scanning involved interacting with plant entities to retrieve their attributes, such as health, age, and type, updating the robot's internal knowledge base.
10. test10_scan_water 5p
    Water scanning enabled the robot to detect and analyze water sources, assessing quality and quantity, which is crucial for environmental management tasks and resource allocation.
11. test11_scan_animal - 6p
    I tried and tried again, in the end... i gave up 
12. test12_scan_object_errors – 2p
    Object scanning errors were tested by attempting to scan non-existent or invalid objects, ensuring appropriate error messages and preventing system crashes during exploration.
13. ..... 21.  Started way to late so here i stop.

<div align="center"><img src="https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExeWt2djVodmJsc3E1c2RqdWc3emV4aGU5OWVrd2g5ZDFvNHdnOHY1MSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/HTCp5FBZ3vEXLDoNOm/giphy.gif" width="500px"></div>