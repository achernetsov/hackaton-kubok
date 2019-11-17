<template>
  <q-page padding>
    <q-card flat bordered class="my-card bg-grey-1">
      <q-card-section>
        <div class="row items-center no-wrap">
          <div class="col">
            <div class="text-h6">Аутентификация</div>
            <div class="text-subtitle2">PROHack4.0</div>
          </div>
        </div>
      </q-card-section>

      <q-card-section>
        <div class="row">
          <text-field
            label="Компания"
            v-model="company"
            placeholder='ООО "Напрастный труд"'
            class="col-12 col-md-6 q-px-md"
          />
          <text-field
            label="Роль"
            v-model="role"
            placeholder="customer"
            class="col-12 col-md-6 q-px-md"
          />
        </div>
      </q-card-section>

      <q-separator />

      <q-card-actions>
        <q-btn color="primary" @click="login">Войти</q-btn>
      </q-card-actions>
    </q-card>
  </q-page>
</template>

<script>
import TextField from "../components/Controls/TextField.vue";
import { mapMutations } from "vuex";
export default {
  // name: 'PageName',
  components: {
    TextField
  },
  data: () => ({
    company: "",
    role: ""
  }),
  methods: {
    login() {
      this.auth({ company: this.company, role: this.role });
      this.$router.push({ path: "/" });
    },
    ...mapMutations({
      auth: "user/auth"
    })
  }
};
</script>
