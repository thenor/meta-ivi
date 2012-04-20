# Copyright (C) 2007 OpenedHand Ltd.
#

DESCRIPTION = "Task for OpenedHand Poky - minimal bootable image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "virtual/kernel"
ALLOW_EMPTY = "1"
PR = "r9"

#
# Set by the machine configuration with packages essential for device bootup
#
MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS ?= ""

# Distro can override the following VIRTUAL-RUNTIME providers:
VIRTUAL-RUNTIME_dev_manager ?= "udev"
VIRTUAL-RUNTIME_login_manager ?= "tinylogin"
VIRTUAL-RUNTIME_init_manager ?= "sysvinit"
VIRTUAL-RUNTIME_initscripts ?= "initscripts"
VIRTUAL-RUNTIME_keymaps ?= "keymaps"

PACKAGES = "\
    task-core-boot-genivi \
    task-core-boot-genivi-dbg \
    task-core-boot-genivi-dev \
"

RDEPENDS_task-core-boot-genivi = "\
    base-files \
    base-passwd \
    busybox \
    ${VIRTUAL-RUNTIME_initscripts} \
    ${@base_contains("MACHINE_FEATURES", "keyboard", "${VIRTUAL-RUNTIME_keymaps}", "", d)} \
    netbase \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${VIRTUAL-RUNTIME_update-alternatives} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS}"

RRECOMMENDS_task-core-boot-genivi = "\
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS}"
