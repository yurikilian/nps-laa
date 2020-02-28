
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
		Describe more details about the solution putting the test and build steps at the diagram. Describe the delivering process after terraform definition.

	Would that solution be ideal? Yes. Honestly by the image I couldn’t get the details behind the process but certainly the usage of terraform and packer is a good choice considering entire distributed systems not for small projects.