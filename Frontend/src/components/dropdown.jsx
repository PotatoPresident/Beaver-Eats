import { Fragment } from 'react'
import { Menu, Transition } from '@headlessui/react'
import { ChevronDownIcon } from '@heroicons/react/20/solid'

function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function Dropdown({location, options}) {
  return (
    <Menu as='div' className='relative inline-block text-left'>

      <Menu.Button className='flex justify-center text-neutral-900 hover:text-neutral-500'>
        {location}
        <ChevronDownIcon className='-mr-1 h-5 w-5 text-neutral-900'/>
      </Menu.Button>


      <Transition
        as={Fragment}
        enter='transition ease-out duration-100'
        enterFrom='transform opacity-0 scale-95'
        enterTo='transform opacity-100 scale-100'
        leave='transition ease-in duration-75'
        leaveFrom='transform opacity-100 scale-100'
        leaveTo='transform opacity-0 scale-95'
      >
        <Menu.Items className=''>
          { options.map((option) => {
            return (
              <Menu.Item key={option.displayName}>
                {({ active }) => (
                  <div
                    
                    className={classNames(
                      active ? 'text-neutral-500' : 'text-neutral-900',
                      'block pl-4 pt-2'
                    )}
                  >
                    <a href='#'>{option.displayName}</a>
                  </div>
                )}
              </Menu.Item>
            )
          })}
        </Menu.Items>
      </Transition>
    </Menu>
  )
}
