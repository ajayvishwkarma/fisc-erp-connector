# Bamboo Specs - Getting Started
This contains a quick guide for getting builds running on your Bamboo instances using Bamboo Specs. You can delete this
file once you are all set up. See further below for background information about Bamboo Specs.


## Update Build Properties
1. Update the [Constants.java](src/main/java/bamboo/Constants.java) file to point to certain bamboo projects, owners
of the repository, etc.
1. Commit and merge this to the main branch.

## Testing on Sandbox Bamboo
Before you go through the pain of Pull Requests that don't work when merging to master causing you take 10 attempts you
can use Sandbox to do some testing.

1. Create a bamboo repository on https://sandbox-bamboo.internal.atlassian.com pointing to your repository
1. Change directory into bamboo-specs
1. Create a .credentials file in your bamboo-specs folder and place your username and password in this file. 
**NOTE:** Please **DO NOT** commit this file and make sure to delete it after you are done.

        username={username}
        password={password}
1. Publish your bamboo spec manually.
        mvn -Ppublish-specs -Dspecs.bamboo.instanceName=sandbox-bamboo -DbambooServerUrl=https://sandbox-bamboo.internal.atlassian.com
        
1. This should publish both your build and test as well as your deployment builds but note that if your repository
requires SOX compliance it will not be able to deploy.

## Setting up on an actual Bamboo
1. Make sure you Constants changes was merged to your main branch.
1. Login to your bamboo environments, e.g. ecosystem and deployment bamboo
1. Add a bamboo repository linking to your repository.
1. Once this is created click the Bamboo Specs table and allow scanning of the repository
1. Give access to all projects.
1. Click the Specs Status tab and scan for specs if you haven't already.
1. Once this is found the plan will be built and run against your main branch.

## Setting up your IntelliJ environment
If you are using IntelliJ as your IDE you will need to do a couple of steps to get it to not give you compilation errors
within it.

1. Open the Maven Tool Window. View -> Tool Windows -> Maven
1. Add bamboo-specs/pom.xml as a maven project.  This will allow for maven compilation to all work.

## What are Bamboo Specs?
If you have experience with Plan Templates written in Groovy you should have no problem converting over to Bamboo specs.
So what does Bamboo Specs gives us? With Plan templates you would have to create a plan that would execute the plan
template to actually generate the plan for your repository.  This meant you had to have two plans for your repository
no matter what, which isn’t the nicest experience.

Bamboo Specs gets around this by having Bamboo scan your repository, pick up any Spec defined in there and execute them.  
Therefore, whenever your code is merged into your main branch, the spec is run on that branch and then your tests are
executed.

For further reading take a look at:
- [Confluence: Bamboo Specs](https://confluence.atlassian.com/bamboo/bamboo-specs-894743906.html)
- [Bamboo Specs - Java Docs](https://docs.atlassian.com/bamboo-specs-docs/6.7.1/specs-java.html)
