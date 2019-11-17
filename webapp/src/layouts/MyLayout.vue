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
          Kubok App
        </q-toolbar-title>

        <div>Kubok Framework v{{ $q.version }}</div>
        <q-btn
          v-if="isAuthenticated"
          label="Выйти из аккаунта"
          @click="logout"
        />
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-grey-2"
    >
      <q-list>
        <q-item-label header>Главное меню:</q-item-label>
        <organisation-menu-item
          v-for="(act, index) in routes"
          :key="index"
          v-bind="act"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import OrganisationMenuItem from "../components/OrganisationMenuItem";
import { mapGetters, mapMutations } from "vuex";
export default {
  name: "MyLayout",
  components: { OrganisationMenuItem },
  data() {
    return {
      leftDrawerOpen: false,
      routes: [{ name: "Создать Акт", route: "/act", icon: "create" }]
    };
  },

  computed: {
    ...mapGetters({
      isAuthenticated: "user/isAuthenticated"
    })
  },
  methods: {
    ...mapMutations({
      logout: "user/logout"
    })
  }
};
</script>
