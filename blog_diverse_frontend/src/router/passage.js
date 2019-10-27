
import HelloWorld from '@/components/index/HelloWorld'
import PassageDetail from '@/components/detail/PassageDetail'
import PassageModify from '@/components/detail/PassageModify'

export default([
    {
        path: '/',
        name: 'HelloWorld',
        component: HelloWorld
    },
    {
        path: '/detail/PassageDetail',
        name: 'PassageDetail',
        component: PassageDetail
    },
    {
      path: '/detail/PassageModify',
      name: 'PassageModify',
      component: PassageModify
    }
  ]
)
