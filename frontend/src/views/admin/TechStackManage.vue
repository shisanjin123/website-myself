<template>
  <div class="tech-stack-manage">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>技术栈管理</span>
          <el-button type="primary" @click="handleAdd">添加技术栈</el-button>
        </div>
      </template>
      <el-table :data="techStacks" stripe>
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="description" label="描述" />
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑技术栈' : '添加技术栈'" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="名称" required>
          <el-input v-model="form.name" placeholder="请输入技术栈名称" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="form.sortOrder" :min="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit" :loading="loading">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getTechStacks, addTechStack, updateTechStack, deleteTechStack } from '../../api/techStack'
import { ElMessage, ElMessageBox } from 'element-plus'

const techStacks = ref([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const loading = ref(false)
const form = ref({
  id: null,
  name: '',
  description: '',
  sortOrder: 0
})

const loadData = async () => {
  try {
    const res = await getTechStacks()
    techStacks.value = res.data || []
  } catch (e) {
    console.error('加载数据失败', e)
  }
}

const handleAdd = () => {
  isEdit.value = false
  form.value = { id: null, name: '', description: '', sortOrder: 0 }
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  form.value = { ...row }
  dialogVisible.value = true
}

const handleDelete = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除吗？', '提示', { type: 'warning' })
    await deleteTechStack(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) {
    if (e !== 'cancel') console.error('删除失败', e)
  }
}

const handleSubmit = async () => {
  if (!form.value.name) {
    ElMessage.warning('请输入名称')
    return
  }
  try {
    loading.value = true
    if (isEdit.value) {
      await updateTechStack(form.value.id, form.value)
    } else {
      await addTechStack(form.value)
    }
    ElMessage.success(isEdit.value ? '更新成功' : '添加成功')
    dialogVisible.value = false
    loadData()
  } catch (e) {
    console.error('操作失败', e)
  } finally {
    loading.value = false
  }
}

onMounted(loadData)
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
