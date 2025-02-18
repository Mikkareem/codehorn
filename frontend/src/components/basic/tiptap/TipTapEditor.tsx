import { EditorProvider } from '@tiptap/react'
import StarterKit from '@tiptap/starter-kit'
import Placeholder from '@tiptap/extension-placeholder'
import TipTapToolbar from './TipTapToolbar'
import { Heading, Pre } from "./extensions";

const editorProps = {
    attributes: {
        class: 'min-h-[200px] p-4 rounded-lg border border-green-500 focus:outline-primary'
    }
}

type TipTapEditorProps = {
    content: string;
    onContentChange: (value: string) => void
    placeholder ?: string;
}

const TipTapEditor = ({ content, onContentChange, placeholder }: TipTapEditorProps ) => {
    const extensions = [
        StarterKit.configure({
            code: {
                HTMLAttributes: {
                    class: 'bg-secondary text-secondary-foreground px-1 rounded',
                }
            }
        }),
        Heading,
        Pre.configure({
            HTMLAttributes: {
                class: 'bg-green-500'
            }
        }),
        Placeholder.configure({ 
            placeholder: placeholder || 'Write Something here.....',
            emptyNodeClass: 'first:before:text-gray-400 first:before:float-left first:before:content-[attr(data-placeholder)] first:before:pointer-events-none first:before:h-0',
        })
    ]

  return (
    <EditorProvider 
        slotBefore={<TipTapToolbar />}
        extensions={extensions}
        content={content}
        editorProps={editorProps}
        immediatelyRender={false}
        onUpdate={({editor}) => {
            onContentChange(editor.getHTML())
        }}
    />
  )
}

export default TipTapEditor