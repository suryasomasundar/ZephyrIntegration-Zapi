# Zapi-Prototype-Java

Requirement

Goal is to create the utility/program having ability to identify the project version in Zephyr and create the test execution management workflow consist of the following steps

Test Cycle Creation
Test Cases Addition to created test cycle
Update the auto test execution results
Identify and log defect in test management tool

Validations Required

Program/Utility should have the capability to take care of below validation

Test Cycle Creation: Verify if Test Cycle already created under the project version in Zephyr. If Yes, no duplicate test cycle should get create under the project else create the required test cycle in the project version
Test Cases Addition: Read all AMMOs (JIRA Ticket No) from the test suite file planned for execution and add the corresponding Jira Test Cases to the created Zephyr Test Cycle. The list can be selective based on the need of execution hence program should deal and add the test cases which are planned & right candidate for execution
Test Case Result Update: Program should be capable enough to read the status from the runtime execution (be it from CI or Physical or Local), trace the correct test case under test cycle in Zephyr and update the test case results recorded at run time.
Defect Management:When a test case gets failed during execution then program should have the ability to create a defect and link the same against the failed test case. Program should also take care no duplicate defect should get raise if the same defect has already been raised against the failed test case.

Workflows/Use Case Handling

Program/Utility should take care of below User Cases and work flows to ensure the end to end test management
Program/Utility should be flagged based (configurable for triggering) or independent to run as a separate pipeline from Jenkins as per the requirement & need
Program/Utility should be configurable or handle the scenario whether to create the new cycle and follow new test cycle or continue to update the test execution results within existing test cycle in Zephyr
Program/Utility should have an integrated JIRA dashboard for test execution such as regression, smoke or release so that update test execution results are automatically reflected on the created dashboard in JIRA

Goals Achieved/Benefits

Create the Zephyr Test cycles automatically in Zephyr to avoid any manual intervention
No manual update of test execution results for release, smoke or regression cycles
Defects can be raised automatically for the failed test case and failed test case would be linked to the raised defect (Need to explore this feature more in detail)

Value Add:
Minimal effort expended for creating test cycles and adding test cases
Auto updation of Jira dashboards with Release test results
Auto updating of Jira dashboards with Smoke + Regression
