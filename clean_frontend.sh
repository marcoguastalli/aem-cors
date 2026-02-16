#!/bin/bash
echo "clean apps clientlibs"
rm -rf ./ui.apps/src/main/content/jcr_root/apps/aemcors/clientlibs/clientlib-dependencies
rm -rf ./ui.apps/src/main/content/jcr_root/apps/aemcors/clientlibs/clientlib-site
echo "clean ui.frontend node"
rm -rf ./ui.frontend/dist
rm -rf ./ui.frontend/node
rm -rf ./ui.frontend/node_modules
