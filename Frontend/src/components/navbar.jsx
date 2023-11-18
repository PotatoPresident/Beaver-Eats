import Beaver from './beaver'
import Dropdown from './dropdown'

export default function Navbar() {
  return (
    <nav className='inline-flex flex-col bg-neutral-50 w-64 h-screen font-GeistRegular'>
      <Beaver className='pl-6'/>
      <div className='flex flex-col pl-6'>
        <span>Locations</span>
        <Dropdown location={'West'} options={['Serranos', 'Ring of Fire', 'Sandwhich']}/>
        <Dropdown location={'McNary'} options={['Garbage']}/>
        <Dropdown location={'Arnold'} options={['Pizza', 'Rice Bowl']}/>
        <Dropdown location={'Memorial Union'} options={['Panda Express', 'Rocket Mortgage']}/>
        <Dropdown location={'Cafes & Markets'} options={['Trader Bings', 'EBGBs']}/>
      </div>
      <div className='pl-6'>Account</div>
      <div className='pl-6'>Settings</div>
    </nav>
  )
}