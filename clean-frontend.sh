#!/bin/bash
set -e

APP_NAME="aemcors"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

echo "Cleaning build artifacts from: $SCRIPT_DIR"

echo "[1/3] Removing webpack-generated clientlibs..."
rm -rf ./ui.apps/src/main/content/jcr_root/apps/$APP_NAME/clientlibs/clientlib-dependencies
rm -rf ./ui.apps/src/main/content/jcr_root/apps/$APP_NAME/clientlibs/clientlib-site

echo "[2/3] Removing ui.frontend distribution..."
rm -rf ./ui.frontend/dist

echo "[3/3] Removing ui.frontend node/ and node_modules/..."
rm -rf ./ui.frontend/node
rm -rf ./ui.frontend/node_modules

echo "Done."
