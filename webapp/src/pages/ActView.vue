<template>
  <q-page padding>
    <q-stepper v-model="step" header-nav ref="stepper" color="primary" animated>
      <q-step
        :name="1"
        title="Внесение параметров договора"
        icon="settings"
        :done="step > 1"
        :header-nav="step > 1"
      >
        <div class="row">
          <text-field
            label="Номер договора"
            v-model="number"
            placeholder="3.14"
            class="col-12 col-md-6 q-px-md"
          />
          <text-field
            label="Дата договора"
            v-model="date"
            placeholder="16.11.2019"
            class="col-12 col-md-6 q-px-md"
          />
          <text-field
            label="Сумма договора"
            v-model="sum"
            placeholder="1"
            class="col-12 col-md-6 q-px-md"
          />
        </div>
        <q-separator />
        <div class="row">
          <text-field
            label="Executor"
            v-model="executor"
            placeholder='ООО "Напрастный труд"'
            class="col-12 col-md-6 q-px-md"
          />
          <text-field
            label="Customer"
            v-model="customer"
            placeholder='ООО "Заплатим немного"'
            class="col-12 col-md-6 q-px-md"
          />
          <text-field
            label="Control"
            v-model="control"
            placeholder='ООО "Нужно переделать"'
            class="col-12 col-md-6 q-px-md"
          />
          <text-field
            label="Accounting"
            v-model="accounting"
            placeholder='ООО "Ждите денег"'
            class="col-12 col-md-6 q-px-md"
          />
        </div>
        <q-separator />

        <text-field
          label="Описание"
          v-model="accounting"
          placeholder="Прошу вас согласовать Акт выполненных работ"
          class="col-12 col-md-6 q-px-md"
        />
        <q-stepper-navigation>
          <q-btn
            @click="sendFirstParams"
            v-if="state === 'NONE'"
            color="primary"
            label="Отправить"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </q-step>
      <q-step
        :name="2"
        title="SLA"
        icon="settings"
        :done="step > 2"
        :header-nav="state !== 'NONE'"
      >
        <div class="row">
          <text-field
            label="Процент выполненных работ"
            :value="sla_percent * 100"
            placeholder='ООО "Ждите денег"'
            class="col-12 col-md-6 q-px-md"
            readonly
          />
          <text-field
            label="Ежемесячная оплата"
            :value="`${sla_monthPay} руб.`"
            placeholder='ООО "Ждите денег"'
            class="col-12 col-md-6 q-px-md"
            readonly
          />
        </div>
      </q-step>
    </q-stepper>
  </q-page>
</template>

<script>
import TextField from "../components/Controls/TextField";
import { mapGetters } from "vuex";
export default {
  components: { TextField },

  // name: 'PageName',
  data: () => ({
    step: 1,

    state: "NONE",

    // First part
    number: "0",
    date: "",
    sum: "",

    executor: "",
    customer: "",
    control: "",
    accounting: "",

    description: "",

    // Second part

    sla_percent: 0.8,
    sla_monthPay: 900.23
  }),
  methods: {
    sendFirstParams() {
      this.step++;
      this.state = "APPROVED";
    }
  },
  computed: {
    ...mapGetters({
      isAuthenticated: "user/isAuthenticated"
    })
  },
  mounted() {
    if (!this.isAuthenticated) {
      this.$router.push({ path: "/login" });
    }
  }
};
</script>

<style lang="stylus">

.block {
  padding 0 10px;
  margin 10px;

  background-color rgba($primary, .05)

  &__caption {
    padding .5rem
    font-size 1.4rem

  }
}
</style>
