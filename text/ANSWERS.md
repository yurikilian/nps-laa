
### LAA - Infra - final solution

What are the good things about that Devops Solution?
Terraform and Packer are used for the devops solution. Even if the job is made for AMI images the packer configuration can gave us no vendor lock in deliver also as Terraform for provisioning. For the fact of using Packer for generating the AMI images we get the correspondent vagrant files that can be used for integration / stress tests before launch.
	
What are the Pain Points?
What are the steps behind Jenkins jobs? Is there build and tests as well as quality analysis (e.g.g sonarqube)? Where this Jekins will be deployed? Is expected this cost?
Didn’t  understand the usage of reds in this case. For a key value storage of terraform configuration? For  cache database for the system or for the Jenkins pipeline? 
What is the process before launch? Canary releases? 
The solution didn’t solve this questions. Thats why I present this in this answer. 
Is this solution for target entire distribution systems? couldn’t it be using containers at container orchestration environments using k8s or docker swarm ? What is the expected infrastructure costs for this project ? Is really necessary the use of AMI images and EC2 environments?
		
Who would you do different? 
Describe more details about the solution putting the test and build steps at the diagram. Describe the delivering process after terraform definition. Would that solution be ideal? Yes. Honestly by the image I couldn’t get the details behind the process but certainly the usage of terraform and packer is a good choice considering entire distributed systems not for small projects.


### Log Access Analytics(LAA)
a. Why your solution scales?
b. What are the core benefits for the business?
c. What are the pain points?
d. How you will attract, motivate and persuade developer to follow your ideas?
e. How can you structure a team to deliver this solution, with which practices and who does what?


All the diagrams are present on diagrams folder.

This project was made following a hybrid approach between traditional microservices and event driven microservices in that way can be scaled horizontal and veritically depending on the costs choice. In one side the microservices instance can be easily increased and registered to a consul discovery service backed by a load balancer in the front as an attempt to reduce the attack surface. For the other side the rabbit mq used as the message bus can be scaled to a cluster to deal with more requests on applications lifecycle. 
Basically the application is separated into a dedicated ingest-service wich is responsible only for the ingest part which create a correspondent log register at mongo db as well as publishes an creation event for the microservi which wants consume for more detailed informaton. Is composed by the analytics aggregator, a not exposed service wich consume and compute the required analytcs and the query service responsible for deal with the large amount of requests and show the metrics. At this point any new need for analytics can be achieved by aggregatin the collections present at mongodb or making a specialized aggregator at batch level. This makes the application easily correspondent to the core business changes and needs for access logs analytics. 
In the same way that mongo db is used for aggregation queries, a perfomatic approach cold be the separation of information data from analytics in an attemptive to reduce the bottleneck present on only one database in the structure. The mongo db log in cluster mode could be used as the input of elasticsearch to be used at Kibana.
The developers can clearly see that my project can be better at the same time I make this initial project in a structure that can be easily refactored to another db structure / messaging bus. Part of this facility is given by the micronaut framework. Also permits that another framework can be used to develop just following the rule to be client of the consul service. This project structure shows to developer how isolated and well tested their work can be because the size of the each microservice. Another ones can be add without large costs, giving space for refactoring and TDD. 
The team responsible for this project could be composed with 3 to 4 developer which 2 of them with management skills (lead / product owner) and 2 of them with technical skills (architect / devops). That way I believe we can achieve a self-managing team who follows a sprint for 2 weeks with kanban respecting the wip of 4 developers and practicing good flow of refactoring and code review. One of them would act as scrum master as could manage the issues and work in development issue to. The second one could work in infrastructure improvements as creation of provisioniong scrips, pipeline management, code quality gates and release process. The another 2 would develop respecting a code template as well as the tests. This team would turn its positions every 2 sprints. That way the knowledge about the business and the process are granted in all parts. All the team is responsible for the deliver. So if there are problems that could not be got on unit / integration tests and was sent to production all the team would be responsible. The developer must participate on all cycles from start to end.
