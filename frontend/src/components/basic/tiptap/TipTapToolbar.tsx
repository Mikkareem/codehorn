
import { useCurrentEditor } from '@tiptap/react'
import {
    Heading3,
    List,
    ListOrdered,
    Code,
    Bold,
    Italic,
    Strikethrough,
} from 'lucide-react'


const TipTapToolbar = () => {
    const { editor } = useCurrentEditor()

    if(!editor) {
        return null
    }

    const activeStyle = 'bg-sky-500 text-bg-foreground'
    const inActiveStyle = 'hover:bg-muted hover:text-bg-muted-foreground'

    const boldStyle = editor.isActive('bold') ? activeStyle : inActiveStyle
    const italicStyle = editor.isActive('italic') ? activeStyle : inActiveStyle
    const heading3Style = editor.isActive('heading', { level: 3 }) ? activeStyle : inActiveStyle
    const listStyle = editor.isActive('bulletList') ? activeStyle : inActiveStyle
    const listOrderedStyle = editor.isActive('orderedList') ? activeStyle : inActiveStyle
    const codeStyle = editor.isActive('code') ? activeStyle : inActiveStyle
    const preCustomStyle = editor.isActive('live') ? activeStyle : inActiveStyle

    const canBoldActive = editor.can().chain().focus().toggleBold().run()
    const canItalicActive = editor.can().chain().focus().toggleItalic().run()
    const canHeading3Active = editor.can().chain().focus().toggleHeading({ level: 3 }).run()
    const canListActive = editor.can().chain().focus().toggleBulletList().run()
    const canListOrderedActive = editor.can().chain().focus().toggleOrderedList().run()
    const canCodeActive = editor.can().chain().focus().toggleCode().run()
    const canPreCustomActive = editor.can().chain().focus().togglePre().run()
    
    const boldActiveClass = canBoldActive ? 'cursor-pointer' : 'cursor-not-allowed'
    const italicActiveClass = canItalicActive ? 'cursor-pointer' : 'cursor-not-allowed'
    const heading3ActiveClass = canHeading3Active ? 'cursor-pointer' : 'cursor-not-allowed'
    const listActiveClass = canListActive ? 'cursor-pointer' : 'cursor-not-allowed'
    const listOrderedActiveClass = canListOrderedActive ? 'cursor-pointer' : 'cursor-not-allowed'
    const codeActiveClass = canCodeActive ? 'cursor-pointer' : 'cursor-not-allowed'
    const preCustomActiveClass = canPreCustomActive ? 'cursor-pointer' : 'cursor-not-allowed'
    
  return (
      <div className='w-full flex gap-3 p-2'>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${boldStyle} ${boldActiveClass}`}
              onClick={() => editor.chain().focus().toggleBold().run()}
          >
              <Bold size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${italicStyle} ${italicActiveClass}`}
              onClick={() => editor.chain().focus().toggleItalic().run()}
          >
              <Italic size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${heading3Style} ${heading3ActiveClass}`}
              onClick={() => editor.chain().focus().toggleHeading({level: 3}).run()}
          >
              <Heading3 size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${codeStyle} ${codeActiveClass}`}
              onClick={() => editor.chain().focus().toggleCode().run()}
          >
              <Code size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${listStyle} ${listActiveClass}`}
              onClick={() => editor.chain().focus().toggleBulletList().run()}
          >
              <List size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${listOrderedStyle} ${listOrderedActiveClass}`}
              onClick={() => editor.chain().focus().toggleOrderedList().run()}
          >
              <ListOrdered size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${preCustomStyle} ${preCustomActiveClass}`}
              onClick={() => editor.chain().focus().togglePre().run()}
          >
              <Strikethrough size={20}/>
          </div>
          <div
              className={`flex justify-center align-middle p-2 rounded-xl ${preCustomStyle} ${preCustomActiveClass}`}
              onClick={() => editor.chain().focus().setHardBreak().run()}
          >
              <Strikethrough size={20}/>
          </div>
      </div>
  )
}

export default TipTapToolbar