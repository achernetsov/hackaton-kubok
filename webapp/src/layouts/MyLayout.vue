<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          icon="menu"
          aria-label="Menu"
        />

        <q-toolbar-title>
          Quasar App
        </q-toolbar-title>

        <div>Quasar v{{ $q.version }}</div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-grey-2"
    >
      <q-list>
        <q-item-label header>Select organization:</q-item-label>
        <organisation-menu-item
          v-for="(org, index) in organisations" :key="index" v-bind="org"/>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import OrganisationMenuItem from "../components/OrganisationMenuItem";
export default {
  name: "MyLayout",
    components: {OrganisationMenuItem},
    data() {
    return {
      leftDrawerOpen: false,
        organisations: [
            { name: "Executor", route: "/executor" },
            { name: "Consumer" },
            { name: "Control" },
            { name: "Accounting" },
        ]
    };
  }
};
</script>
