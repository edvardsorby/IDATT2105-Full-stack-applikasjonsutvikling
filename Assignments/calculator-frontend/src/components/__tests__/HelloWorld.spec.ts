import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import LogIn from '../LogIn.vue'

describe('HelloWorld', () => {
  it('renders properly', () => {
    const wrapper = mount(LogIn)
    expect(wrapper.text()).toContain("Brukernavn")
  })
})
