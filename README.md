# AEMaaCS project with CORS enabled

I had to expose some json endpoint from AEM to external consumers.

I had CORS issues, the Adobe documentation and examples are not clear.

They don't use valid Regular Expressions, they do not escape reg-exp chars.

So it's even harder to achieve the goal from external host / origin.

After tests and error I've found this configuration.

It works :)

And not only in my local.

It works in the Adobe Cloud Manager.

You can GET or POST a json from one AEMaaCS publish environment to another AEMaaCS, like from PROD to STAGE, to DEV, to RDE, etc..

If you have a better configuration feel free to open my a Pull Request.

Thank you, Cheers


## How to build and deploy
To build all the modules run in the project root directory the following command with maven:
- `mvn clean install -PautoInstallSinglePackage -Daem.port=4502`
- `mvn clean install -PautoInstallSinglePackage -Daem.port=4503`

In order to skip the JUnit tests add the following: `-DskipTests`:
- `mvn clean install -PautoInstallSinglePackage -Daem.port=4502 -DskipTests`
- `mvn clean install -PautoInstallSinglePackage -Daem.port=4503 -DskipTests`

## Deploy and run Dispatcher
- `colima start --cpu 4 --memory 8`
- `chmod a+rwx dispatcher-sdk-2.0.258/`
- `~/dev/repository/git/aem-cors/sync_dispatcher_src.sh ~/dev/repository/git/aem-cors/dispatcher/src ~/opt/aem-servers-cloud/dispatcher-sdk-2.0.258/src`
- `cd dispatcher-sdk-2.0.258/`
- `./bin/validate.sh ./src`
- `./bin/docker_run_hot_reload.sh ./src host.docker.internal:4503 80`

## Check CORS
- `curl -i -X POST -H "Content-Type: application/json" -d '{"paths":["/content/core-components-examples"]}' http://localhost/bin/aemcors/search/pagereferences.json`
