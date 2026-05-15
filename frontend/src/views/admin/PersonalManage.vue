<template>
  <div class="personal-manage">
    <el-card>
      <template #header>个人介绍管理</template>
      <el-form :model="form" label-width="100px">
        <el-form-item label="标题">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="欢迎语">
          <el-input v-model="form.welcomeText" type="textarea" :rows="3" placeholder="请输入欢迎语" />
        </el-form-item>
        <el-form-item label="个人介绍">
          <el-input v-model="form.content" type="textarea" :rows="5" placeholder="请输入个人介绍" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSave" :loading="loading">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getPersonalIntro, updatePersonalIntro } from '../../api/personal'
import { ElMessage } from 'element-plus'

const form = ref({
  id: null,
  title: '',
  welcomeText: '',
  content: ''
})
const loading = ref(false)

onMounted(async () => {
  try {
    const res = await getPersonalIntro()
    if (res.data) {
      form.value = res.data
    }
  } catch (e) {
    console.error('加载数据失败', e)
  }
})

const handleSave = async () => {
  try {
    loading.value = true
    await updatePersonalIntro(form.value)
    ElMessage.success('保存成功')
  } catch (e) {
    console.error('保存失败', e)
  } finally {
    loading.value = false
  }
}
</script>
