# RSEG0127_Spring2021

GitLab project for library system class project

## 2021-02-18
To run the application locally...

1. Install MariaDB
2. Run SQL files in this order:
    1. ddl.sql
    2. schema.sql
    3. initial-data.sql
3. `mvn clean install`
4. `mvn spring-boot:run`
5. Navigate to `localhost:8080` in a browser

## 2021-02-23
### Setting up a local GitLab runner
**These are macOS-specific instructions**
* first, install [Homebrew](https://brew.sh/)

1. `brew install gitlab-runner` in your terminal 
2. in the sidebar, go to **Settings** (gear icon) > **CI/CD**
    * _(NOT the CI/CD with the rocket icon)_
3. expand **Runners** section
4. under **Specific Runners**, go to **Set up a specific runner manually**
5. run `gitlab-runner register` in your terminal
6. fill in the blanks - use the URL/token in GitLab
    * For **executor**, type `shell`

### Testing the example script
#### This will change in the future...
1. cd to the top level directory of the project (`rseg0127_spring2021`)
2. run `gitlab-runner exec shell deploy-prod` in your terminal

