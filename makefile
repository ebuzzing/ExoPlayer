GRADLE=./gradlew
PROJECT_ROOT=.
PROJECT_SOURCE_NAME=library-core

PROJECT_LIB=$(PROJECT_ROOT)/build
PROJECT_SOURCE=$(PROJECT_ROOT)/$(PROJECT_SOURCE_NAME)

VERSION_FILE=$(PROJECT_ROOT)/VERSION

NO_COLOR=\x1b[0m
OK_COLOR=\x1b[32;01m
ERROR_COLOR=\x1b[31;01m
WARN_COLOR=\x1b[33;01m
OK_STRING=$(OK_COLOR)OK: $(NO_COLOR)
ERROR_STRING=$(ERROR_COLOR)ERROR: $(NO_COLOR)
WARN_STRING=$(WARN_COLOR)WARNING: $(NO_COLOR)


all: unknowCommand

release: clean build upload

.PHONY: build
build:
	@echo 'Building ...'

	$(GRADLE) $(PROJECT_SOURCE_NAME):build
upload:
	@echo 'bintrayUpload ...'
	$(GRADLE) $(PROJECT_SOURCE_NAME):bintrayUpload

unknowCommand:
	@echo "$(ERROR_STRING) unknow flavor/option";
	@echo "Use one of the following command:";
	@echo "  - $(OK_COLOR)release        $(NO_COLOR)     release without updating versions";

clean:
	@echo 'Cleaning ...'
	@rm -rf $(PROJECT_LIB)
	@rm -f $(PROJECT_SOURCE)/build/libs/$(PROJECT_SOURCE_NAME).jar 
	$(GRADLE) clean
	@echo 'Clean Successful'
