**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:      |     |
| -------------- | --- |
| Student Names: | Rakshita Suri  |
|                | Mohit Kaila   |
|                |Okibe Abang     |
|                |     |

# Introduction
This lab focuses on two key ways to test software: Mutation Testing and Automated GUI/Web Testing. We work on the same test suite as assignment 3 and try to enhance the tests. In the first part, we use Pitest to check and improve test sets for the Range and DataUtilities classes. We look at mutation scores to see how good the tests are and make the tests better to cover more ground. We follow various techniques to increase the score. We try to write tests that check a varied range of inputs. We analyze mutants that are killed by the original test suite from assignment 3, and also observe the mutants that are not killed. The second part is about automated UI testing. Here, we use Selenium IDE to write and run test scripts that check if a web app works correctly. The website that we focus on is Home Depot: https://www.homedepot.ca. We check various features of this website and generate scripts to carry out automated testing. We also compare Selenium with Sikulix, pointing out what makes them good at testing GUI. The goal of this lab is to learn more about software testing by improving test sets with mutation testing and using automated tools to make web testing faster and more reliable.

# Analysis of 10 Mutants of the Range class 

# Report all the statistics and the mutation score for each test class



# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

# A discussion of what could have been done to improve the mutation score of the test suites

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

# Explain your SELENUIM test case design process

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix
Selenium and SikuliX are both tools for test automation, but they work differently. Selenium is good for testing websites since it runs well on browsers like Chrome, Firefox, and Edge. It supports different programming languages, works well with other testing tools, and is pretty fast. The issue with Selenium is that it works good only for web apps. It needs some extra setup, and can’t do anything outside the webpage. SikuliX is different because it looks for images on the screen to interact with things, which makes it useful for automating desktop apps, older software, and even games. It’s pretty simple to use and doesn’t require much setup, but it’s slower, depends on screen resolution, and isn’t the best for larger projects. If the goal is web testing, Selenium is the better option, while SikuliX is great for anything that appears on the screen. In some cases, using both can create a smoother and more flexible testing experience.

# How the team work/effort was divided and managed
For Pit testing, we divided the work equally by assigning the Range file to two members and Data Utilities file to the other two. For GUI testing, we first made the test plan and worked according to the plan to explore the functionalities. This way, we made sure that the functionalities we tested didn't overlap. In order to manage our work and collaborate, we used Github. This made version control easy as well. We communicated using a discord group chat and did check-ins in between to make sure we were on the right track. 

# Difficulties encountered, challenges overcome, and lessons learned
We faced initial challenges with the PIT testing, because 2 of our members had issues in making a test suite. This prevented PIT testing and we couldn't get the report. However, once we had the correct structure of the suite, we were on the right track. Another issue that we faced was with using selenium extension. It made the browser really slow and we had to close the project often and come back to it. We overcame these challenges with team work, collective effort, and effective communication. 

# Comments/feedback on the lab itself
The lab was well designed and quite interesting. By the end, we were confident with our testing skills. It enhanced our ability to work in a group and complete the tasks with others. Overall, each part of the lab was easy to implement using the instructions. 
