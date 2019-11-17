<template>
  <q-page padding>
    <q-stepper v-model="step" header-nav ref="stepper" color="primary" animated>
      <q-step
        :name="1"
        title="Внесение параметров договора"
        icon="assignment_ind"
        :done="step > 1"
        :header-nav="step > 1"
      >
        <div class="row">
          <text-field
            label="Наименование договора"
            v-model="dname"
            placeholder="3.14"
            class="col-12 col-md-6 q-px-md"
            :readonly="state != 'NONE'"
          />
          <!-- <text-field
            label="Сумма договора"
            v-model="sum"
            placeholder="1"
            class="col-12 col-md-6 q-px-md"
            :readonly="state != 'NONE'"
          /> -->
        </div>
        <q-separator />
        <div class="row">
          <text-field
            label="Executor"
            v-model="executor"
            placeholder='ООО "Напрастный труд"'
            class="col-12 col-md-6 q-px-md"
            :readonly="state != 'NONE' || ($store.state.user.user.role === 'executor')"
          />
          <text-field
            label="Customer"
            v-model="customer"
            placeholder='ООО "Заплатим немного"'
            class="col-12 col-md-6 q-px-md"
            :readonly="state != 'NONE'"
          />
          <q-separator />

          <text-field
            label="Стоимость услуги (плановая)"
            :value="payAmountPlan"
            class="col-12 col-md-6 q-px-md"
            readonly
          />
          <text-field
            label="Стоимость услуги (итоговая)"
            :value="payAmountFact"
            class="col-12 col-md-6 q-px-md"
            readonly
          />
          <text-field
            label="Процент выполненных работ"
            v-model="sla_percent"
            placeholder='ООО "Ждите денег"'
            class="col-12 col-md-6 q-px-md"
          />
          <!-- <text-field
            label="Control"
            v-model="control"
            placeholder='ООО "Нужно переделать"'
            class="col-12 col-md-6 q-px-md"
            :readonly="state != 'NONE'"
          />
          <text-field
            label="Accounting"
            v-model="accounting"
            placeholder='ООО "Ждите денег"'
            class="col-12 col-md-6 q-px-md"
            :readonly="state != 'NONE'"
          />-->
        </div>
        <!-- <q-separator /> -->

        <!-- <text-field
          label="Описание"
          v-model="description"
          placeholder="Прошу вас согласовать Акт выполненных работ"
          class="col-12 col-md-6 q-px-md"
          type="textarea"
            :readonly="state != 'NONE'"
        /> -->
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
      <!-- <q-step
        :name="2"
        title="SLA"
        icon="assignment"
        :done="step > 2"
        :header-nav="state !== 'NONE'"
      >
        <div class="row">

           <text-field
            label="Ежемесячная оплата"
            :value="`${sla_monthPay} руб.`"
            placeholder='ООО "Ждите денег"'
            class="col-12 col-md-6 q-px-md"
            readonly
          />
        </div>
      </q-step> -->
      <!-- <q-step
        :name="3"
        title="Акт выполненных работ"
        icon="assessment"
        :done="step > 2"
        :header-nav="state !== 'NONE'"
      >
        <div class="row">
          <text-field
            label="Наименование акта"
            :value="workAct_name"
            class="col-12 col-md-6 q-px-md"
            readonly
          />
          <text-field
            label="Кол-во оказанных услуг"
            :value="workAct_servicesCount"
            class="col-12 col-md-6 q-px-md"
            readonly
          />
          <q-separator />
        </div>
      </q-step> -->

      <q-step
        :name="4"
        title="Решение заказчика"
        icon="assignment_late"
        :done="step > 2"
        :header-nav="state !== 'NONE'"
      >
        <div class="row">
          <text-field
            label="UUID"
            :value="uuid"
            class="col-12 col-md-6 q-px-md"
            readonly
          />
          <text-field
            label="Дата создания акта"
            :value="createdAt"
            class="col-12 col-md-6 q-px-md"
            readonly
          />
        </div>
        <template v-if="$store.state.user.user.role === 'customer'">
          <q-btn label="Подтвердить" />
          <q-separator />
          <q-btn label="Мотивированный отказ" />
          <text-field
            label="Причина отказа"
            :value="customer_reject_reason"
            class="col-12 col-md-6 q-px-md"
          />
        </template>
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
    dname: "",
    // sum: "",

    executor: "",
    customer: "",
    // control: "",
    // accounting: "",

    payAmountPlan: 0,
    payAmountFact: 0,
    //description: "",

    // Second part

    sla_percent: 0.8,
    //sla_monthPay: 900.23,

    // Акт выполннных работ
    workAct_name: "",
    workAct_servicesCount: "",

    // acceptance
    uuid: "",
    createdAt: "",
    customer_reject_reason: ""
  }),
  methods: {
    async sendFirstParams() {
      const respData = await this.$axios.post(
        "http://634cdc6d.ngrok.io/act",
        JSON.stringify({
          name: this.dname,
          executor: this.executor,
          customer: this.customer,
          sla: 99
        })
      );
      this.uuid = respData.data.uuid;
      this.createdAt = respData.data.dateTime;
      this.state = respData.data.state;
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
      return;
    }
    if (this.$store.state.user.user.role === 'executor') {
      this.executor = this.$store.state.user.user.company;
    }
  }
};
</script>
