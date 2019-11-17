<template>
  <q-item clickable @click="click">
    <q-item-section avatar>
      <q-icon :name="icon" />
    </q-item-section>
    <q-item-section>
      <q-item-label>{{ name }}</q-item-label>
      <q-item-label caption>{{ caption }}</q-item-label>
    </q-item-section>
  </q-item>
</template>

<script>
export default {
  name: "OrganisationMenuItem",
  props: {
    route: String,
    name: String,
    caption: String,
    icon: String
  },
  methods: {
    click() {
      this.$router.push(this.route);
    }
  }
};
</script>

<style scoped></style>
